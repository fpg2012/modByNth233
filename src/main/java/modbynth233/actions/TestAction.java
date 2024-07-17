package modbynth233.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class TestAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private int damage;
    AbstractMonster target;
    private AbstractCard cardToObtain;
    private int tickCount = 0;
    private int maxTick = 2;
    private DamageInfo info;

    public TestAction(int damage, int maxTick, AbstractMonster target, AbstractCard cardToObtain) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
        this.cardToObtain = cardToObtain;
        this.maxTick = maxTick;
        this.target = target;
        this.damage = damage;
        this.info = new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL);
    }

    public void update() {
        if (tickCount < maxTick) {
            if (tickCount > 0) {
                if (this.target.lastDamageTaken > 0) {
                    this.gainDexterityAndChance();
                }
            }

            this.tickDuration();

            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.POISON, false));
            this.target.damage(this.info);

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            } else {
                this.addToTop(new WaitAction(0.1F));
            }

            addToTop(new WaitAction(0.2F));

            tickCount++;
            this.tickDuration();
        } else {
            if (this.target.lastDamageTaken > 0) {
                this.gainDexterityAndChance();
            }
            this.isDone = true;
        }
    }

    private void gainDexterityAndChance() {
        if (this.target.getIntentBaseDmg() >= 0) {
            this.addToTop(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, 1)));
        } else {
            this.addToTop(new MakeTempCardInHandAction(this.cardToObtain.makeStatEquivalentCopy(), 1));
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("modByNth233:DisposeAction");
        TEXT = uiStrings.TEXT;
    }
}
