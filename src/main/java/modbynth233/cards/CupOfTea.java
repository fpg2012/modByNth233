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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class CupOfTea extends MyBaseCard {
    public static final String ID = makeID(CupOfTea.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 1;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public CupOfTea() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        for (int i = 0; i < p.powers.size(); i++) {
            if (p.powers.get(i).getClass().equals(ThornsPower.class)) {
                amount = p.powers.get(i).amount;
                break;
            }
        }
        if (amount > 0) {
            addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, amount), amount));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CupOfTea();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        setExhaust(false);
    }
}
