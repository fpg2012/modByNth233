package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Bump extends MyBaseCard {
    public static final String ID = makeID(Bump.class.getSimpleName());
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 0;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public Bump() {
        super(ID, info, 0, 4);
        setDamage(DAMAGE);
        setCostUpgrade(UPG_COST);
        upgradeDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new WaitAction(0.1F));

        int amount = 0;
        for (int i = 0; i < p.powers.size(); i++) {
            if (p.powers.get(i).getClass().equals(ThornsPower.class)) {
                amount = p.powers.get(i).amount;
                break;
            }
        }

        if (amount > 0) {
            addToBot(new DamageAction(m, new DamageInfo(p, amount, DamageInfo.DamageType.THORNS)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bump();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
