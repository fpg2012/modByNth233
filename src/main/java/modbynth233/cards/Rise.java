package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Rise extends MyBaseCard {
    public static final String ID = makeID(Rise.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 5;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 0;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public Rise() {
        super(ID, info);
        setMagic(MAGIC_NUMBER);
        setCostUpgrade(UPG_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        for (int i = 0; i < p.powers.size(); i++) {
            if (p.powers.get(i).getClass().equals(DexterityPower.class)) {
                amount = p.powers.get(i).amount;
                break;
            }
        }
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -1), -1));

        if (amount != 0) {
            amount = Math.abs(amount);
            addToBot(new DamageAction(m, new DamageInfo(p, amount * this.magicNumber, DamageInfo.DamageType.NORMAL)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Rise();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
