package modbynth233.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.actions.DrawCardFromDiscardPileAction;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Trick extends BaseCard {
    public static final String ID = makeID(Trick.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 0;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Trick() {
        super(ID, info);
        setMagic(MAGIC_NUMBER);
        setCostUpgrade(UPG_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardFromDiscardPileAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Trick();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
