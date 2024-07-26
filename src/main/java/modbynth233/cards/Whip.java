package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Whip extends BaseCard {
    public static final String ID = makeID(Whip.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPG_MAGIC_NUMBER = -1;
    private static final int UPG_COST = 1;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public Whip() {
        super(ID, info);
        setCostUpgrade(UPG_COST);
        upgradeMagic = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        for (int i = 0; i < m.powers.size(); i++) {
            if (p.powers.get(i).getClass().equals(ConstrictedPower.class)) {
                amount = p.powers.get(i).amount;
                break;
            }
        }

        if (amount > 0) {
            addToBot(new DamageAction(m, new DamageInfo(p, amount, DamageInfo.DamageType.NORMAL)));
            addToBot(new WaitAction(0.1F));
            addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, -this.magicNumber), -this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Whip();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
