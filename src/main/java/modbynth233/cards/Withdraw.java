package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Withdraw extends MyBaseCard {
    public static final String ID = makeID(Withdraw.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 2;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPG_MAGIC_NUMBER = 1;
    private static final int UPG_COST = 1;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public Withdraw() {
        super(ID, info);
        setBlock(BLOCK);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        upgradeMagic = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new GainBlockAction(p, this.block));
            this.addToBot(new WaitAction(0.2F));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Withdraw();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}

