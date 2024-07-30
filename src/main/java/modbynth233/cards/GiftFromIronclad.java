package modbynth233.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import com.megacrit.cardcrawl.vfx.combat.StunStarEffect;
import modbynth233.ModByNth233;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class GiftFromIronclad extends MyBaseCard {
    public static final String ID = makeID(GiftFromIronclad.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.RED,
            CardType.CURSE,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 200;
    private static final int UPG_DAMAGE = 20;
    private static final int MAGIC_NUMBER = 2;
    private static final int MAGIC_NUMBER_UPG = -1;
    private static final AbstractCard cardToObtain = new Dazed();

    public GiftFromIronclad() {
        super(ID, info);
        this.portraitImg = null;
        this.portrait = ModByNth233.cardAtlas.findRegion("red/power/demon_form");
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC_NUMBER, MAGIC_NUMBER_UPG);
        tags.add(CardTags.STRIKE);
        upgradeDamage = true;
        upgradeMagic = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new GrandFinalEffect(), 0.7F));
        } else {
            this.addToBot(new VFXAction(new GrandFinalEffect(), 1.0F));
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new TalkAction(true, cardStrings.EXTENDED_DESCRIPTION[0], 2, 2));
        addToBot(new MakeTempCardInDrawPileAction(new Dazed(), this.magicNumber, true, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GiftFromIronclad();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}