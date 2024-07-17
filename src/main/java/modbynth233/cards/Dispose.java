package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.actions.DisposeAction;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Dispose extends BaseCard {
    public static final String ID = makeID(Dispose.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = 1;
    private static final int UPG_COST = 1;
    private boolean randomExhaust = true;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Dispose() {
        super(ID, info);
        setMagic(MAGIC_NUMBER);
//        upgradeMagic = true;
        randomExhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DisposeAction(this.magicNumber, this.randomExhaust));
        this.addToBot(new DrawCardAction(1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Dispose();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        randomExhaust = false;
    }
}
