package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.actions.AddToExhaustAction;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Flashback extends MyBaseCard {
    public static final String ID = makeID(Flashback.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 1;
    private final AbstractCard cardToObtain = new Nostalgia();
    private AbstractCard recall = new Recall();

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2
    );

    public Flashback() {
        super(ID, info, 1, 7);
        setExhaust(true);
        this.cardsToPreview = cardToObtain;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddToExhaustAction(new Nostalgia()));
        addToBot(new WaitAction(0.2F));
        addToBot(new MakeTempCardInDrawPileAction(this.recall.makeStatEquivalentCopy(), 1, true, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flashback();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.recall.upgrade();
    }
}
