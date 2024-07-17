package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.actions.PreparationAction;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Preparation extends BaseCard {
    public static final String ID = makeID(Preparation.class.getSimpleName());
//    private static final int DAMAGE = 0;
//    private static final int UPG_DAMAGE = 0;
//    private static final int BLOCK = 0;
//    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 1;
//    private static final int UPG_COST = 1;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            -1
    );

    public Preparation() {
        super(ID, info);
//        setDamage(DAMAGE, UPG_DAMAGE);
//        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
//        setCostUpgrade(UPG_COST);
//         tags.add(CardTags.STRIKE);
        upgradeMagic = true;
//        upgradeBlock = true;
//        upgradeDamage = true;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PreparationAction(p, this.freeToPlayOnce, this.energyOnUse, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Preparation();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}

