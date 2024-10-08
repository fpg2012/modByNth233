package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import modbynth233.ModByNth233;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class TurnAroundStrike extends MyBaseCard {
    public static final String ID = makeID(TurnAroundStrike.class.getSimpleName());
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = 1;
    private static final int UPG_COST = 1;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public TurnAroundStrike() {
        super(ID, info);
        this.portraitImg = null;
        this.portrait = ModByNth233.cardAtlas.findRegion("purple/attack/follow_up");
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        tags.add(CardTags.STRIKE);
        upgradeMagic = true;
        upgradeDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new WaitAction(0.1F));
        addToBot(new DrawCardAction(this.magicNumber));
        addToBot(new WaitAction(0.1F));
        addToBot(new DiscardAction(p, p, 1, false));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TurnAroundStrike();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
