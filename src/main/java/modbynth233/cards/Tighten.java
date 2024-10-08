package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Tighten extends MyBaseCard {
    public static final String ID = makeID(Tighten.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPG_MAGIC_NUMBER = 2;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );

    public Tighten() {
        super(ID, info, 4, 3);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        setExhaust(true);
        upgradeMagic = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        for (int i = 0; i < m.powers.size(); i++) {
            if (m.powers.get(i).getClass().equals(ConstrictedPower.class)) {
                amount = m.powers.get(i).amount;
                break;
            }
        }

        addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, amount * (this.magicNumber - 1)), amount * (this.magicNumber - 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Tighten();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
