package modbynth233.cards;

import basemod.devcommands.deck.DeckRemove;
import basemod.devcommands.relic.RelicAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Nostalgia extends MyBaseCard {
    public static final String ID = makeID(Nostalgia.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.CURSE,
            CardRarity.CURSE,
            CardTarget.SELF,
            2
    );

    public Nostalgia() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        for (int i = 0; i < p.powers.size(); i++) {
            if (p.powers.get(i).getClass().equals(DexterityPower.class)) {
                amount = p.powers.get(i).amount;
                break;
            }
        }

        if (amount > 0) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, amount), amount));
        }
    }

    @Override
    public void onRemoveFromMasterDeck() {
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), RelicLibrary.getRelic("Dead Branch").makeCopy());
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), RelicLibrary.getRelic("Prismatic Shard").makeCopy());
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), RelicLibrary.getRelic("Burning Blood").makeCopy());
        super.onRemoveFromMasterDeck();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Nostalgia();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

}
