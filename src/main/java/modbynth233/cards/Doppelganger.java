package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.DoppelgangerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Doppelganger extends MyBaseCard {
    public static final String ID = makeID(Doppelganger.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 1;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            -1
    );

    public Doppelganger() {
        super(ID, info);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        upgradeMagic = true;
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        int drawNumber = this.energyOnUse + this.magicNumber;
//        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, drawNumber), drawNumber));
//        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, drawNumber), drawNumber));
        addToBot(new DoppelgangerAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Doppelganger();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
