package modbynth233.event;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.beyond.Falling;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import modbynth233.ModByNth233;
import modbynth233.cards.GiftFromIronclad;

public class GiftFromIroncladEvent extends AbstractImageEvent {
    public static final String ID = ModByNth233.modID + ":" + "GiftFromIroncladEvent";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    //This text should be set up through loading an event localization json file
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String NAME = eventStrings.NAME;
    private GiftFromIroncladEvent.CurScreen screen;
    private static AbstractCard dazed = new Dazed();
    private static AbstractCard gift = new GiftFromIronclad();
    private static AbstractCard wound = new Wound();

    public GiftFromIroncladEvent() {
        super(NAME, DESCRIPTIONS[0], "images/events/falling.jpg");
        this.screen = CurScreen.INTRO;
        //This is where you would create your dialog options
        this.imageEventText.setDialogOption(OPTIONS[0]); //This adds the option to a list of options
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        //It is best to look at examples of what to do here in the basegame event classes, but essentially when you click on a dialog option the index of the pressed button is passed here as buttonPressed.
        switch (this.screen) {
            case INTRO:
                this.screen = CurScreen.CHOICE;
                this.imageEventText.clearAllDialogs();
                this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                this.imageEventText.setDialogOption(OPTIONS[1] + FontHelper.colorString(dazed.name, "r") + OPTIONS[2] + FontHelper.colorString(gift.name, "g"), gift.makeStatEquivalentCopy());
                this.imageEventText.setDialogOption(OPTIONS[3] + FontHelper.colorString("8", "r") + OPTIONS[4] + OPTIONS[5] + FontHelper.colorString(wound.name, "r"), wound.makeStatEquivalentCopy());
                break;
            case CHOICE:
                this.screen = CurScreen.RESULT;
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[6]);
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        for (int i = 0; i < 12; ++i) {
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(dazed.makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        }
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(gift.makeStatEquivalentCopy(), (float)Settings.WIDTH / 3.0F * 2.0F, (float)Settings.HEIGHT / 3.0F));
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(wound.makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, 8, DamageInfo.DamageType.HP_LOSS));
                        break;
                    default:
                        return;
                }
                break;
            default:
                this.openMap();
        }
    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_FALLING");
        }
    }

    private static enum CurScreen {
        INTRO,
        CHOICE,
        RESULT;

        private CurScreen() {
        }
    }
}