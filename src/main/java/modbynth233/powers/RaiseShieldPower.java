package modbynth233.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import modbynth233.cards.SwiftBlock;

import static modbynth233.ModByNth233.makeID;

public class RaiseShieldPower extends AbstractPower {
    public static final String POWER_ID = makeID("RaiseShieldPower");
    private static PowerStrings powerStrings = null;
    private static PowerStrings getPowerStrings(String ID) {
        return CardCrawlGame.languagePack.getPowerStrings(ID);
    };

    public RaiseShieldPower(AbstractCreature owner, int amount) {
        if (powerStrings == null) {
            powerStrings = getPowerStrings(POWER_ID);
        }
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("afterImage");
        this.updateDescription();
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(new SwiftBlock(), this.amount, false));
        }
    }
    public void updateDescription() {
        if (this.amount > 1) {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
        } else {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2];
        }
    }


    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }
}
