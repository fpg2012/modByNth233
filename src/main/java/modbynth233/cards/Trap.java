package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Trap extends BaseCard {
    public static final String ID = makeID(Trap.class.getSimpleName());
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            3
    );

    public Trap() {
        super(ID, info);
        setCostUpgrade(2);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new SlowPower(m, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Trap();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
