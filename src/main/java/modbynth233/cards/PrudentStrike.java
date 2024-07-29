package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class PrudentStrike extends MyBaseCard {
    public static final String ID = makeID(PrudentStrike.class.getSimpleName());
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 1;
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 1;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public PrudentStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        tags.add(CardTags.STRIKE);
        upgradeBlock = true;
        upgradeDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new WaitAction(0.1F));
        addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PrudentStrike();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
