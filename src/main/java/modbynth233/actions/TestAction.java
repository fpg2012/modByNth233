package modbynth233.actions;

import com.badlogic.gdx.Gdx;
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
//    private static final UIStrings uiStrings;
//    public static final String[] TEXT;
    private AbstractPlayer p;
    private int damage;
    AbstractMonster target;
    private AbstractCard cardToObtain;
    private int chainCount = -1;
    private int maxChain = 3;
    private DamageInfo info;

    public TestAction(int damage, int maxChain, AbstractMonster target, AbstractCard cardToObtain) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
        this.cardToObtain = cardToObtain;
        this.maxChain = maxChain;
        this.target = target;
        this.damage = damage;
        this.info = new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL);
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > 0.0F) {
            return;
        }

        this.duration = Settings.ACTION_DUR_FAST;

        if (chainCount == -1 || chainCount < maxChain && this.target.lastDamageTaken <= 0) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.POISON, false));
            this.target.damage(this.info);

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            chainCount++;
        } else {
            if (chainCount == 0) {
                this.gainChance();
            }
            this.isDone = true;
        }
    }

    private void gainChance() {
        this.addToTop(new MakeTempCardInHandAction(this.cardToObtain.makeStatEquivalentCopy(), 1));
    }
}
