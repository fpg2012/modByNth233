package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Chance extends MyBaseCard {
    public static final String ID = makeID(Chance.class.getSimpleName());
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
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );

    public Chance() {
        super(ID, info);
        setEthereal(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m == null) {
            return;
        }
        if (m.getIntentBaseDmg() < 0) {
            addToBot(new DrawCardAction(2));
            if (this.upgraded) {
                addToBot(new GainEnergyAction(1));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Chance();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
