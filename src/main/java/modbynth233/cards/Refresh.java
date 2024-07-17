package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Refresh extends BaseCard {
    public static final String ID = makeID(Refresh.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPG_MAGIC_NUMBER = 3;
    private static final int UPG_COST = 1;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public Refresh() {
        super(ID, info);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        setCostUpgrade(UPG_COST);
        setExhaust(true);
        upgradeMagic = true;
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
        if (amount > 0) {
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -amount), -amount));
            addToBot(new GainBlockAction(p, amount * this.magicNumber));
            addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1), 1));
        } else if (amount < 0) {
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -amount), -amount));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Refresh();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        setExhaust(false);
    }
}