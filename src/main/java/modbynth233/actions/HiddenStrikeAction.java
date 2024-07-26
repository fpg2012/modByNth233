package modbynth233.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class HiddenStrikeAction extends AbstractGameAction {
    private final DamageInfo info;
    private final int magicNumber;
    private int count = 0;

    public HiddenStrikeAction(AbstractCreature source, AbstractCreature target, DamageInfo info, int magicNumber) {
        this.info = info;
        this.setValues(target, info);
        this.source = source;
        this.actionType = ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
        this.magicNumber = magicNumber;
        this.count = 0;
    }

    public void update() {
        if (this.shouldCancelAction()) {
            this.isDone = true;
        } else {
            if (this.count == 0) {
                addToBot(new ApplyPowerAction(target, source, new VulnerablePower(target, this.magicNumber, false), this.magicNumber));
                addToBot(new WaitAction(0.2F));
                count++;
                this.tickDuration();
            } else if (this.count == 1) {
                addToBot(new DamageAction(target, this.info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                addToBot(new WaitAction(0.2F));
                this.isDone = true;
            }
        }
    }
}
