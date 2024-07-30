package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.actions.AddToExhaustAction;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Reflect extends MyBaseCard {
    public static final String ID = makeID(Reflect.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 0;
    private final AbstractCard cardToObtain = new Recall();

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Reflect() {
        super(ID, info);
        setCostUpgrade(UPG_COST);
        this.cardsToPreview = new OrdinaryForm();
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddToExhaustAction(this.cardsToPreview));
        addToBot(new MakeTempCardInDrawPileAction(this.cardToObtain.makeStatEquivalentCopy(), 1, true, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Reflect();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.cardToObtain.upgrade();
    }
}
