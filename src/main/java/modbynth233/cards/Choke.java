package modbynth233.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Choke extends MyBaseCard {
    public static final String ID = makeID(Choke.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPG_MAGIC_NUMBER = 1;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public Choke() {
        super(ID, info);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
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

        int fadeAmt = (int) -Math.ceil((double) amount/this.magicNumber);
        if (amount > 0) {
            addToBot(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, fadeAmt), fadeAmt));
            addToBot(new StunMonsterAction(m, p));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Choke();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
