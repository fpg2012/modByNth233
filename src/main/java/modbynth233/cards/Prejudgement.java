package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Prejudgement extends MyBaseCard {
    public static final String ID = makeID(Prejudgement.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 8;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = 1;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Prejudgement() {
        super(ID, info, 2, 8);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
        addToBot(new WaitAction(0.1F));
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));
        addToBot(new WaitAction(0.1F));
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Prejudgement();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
