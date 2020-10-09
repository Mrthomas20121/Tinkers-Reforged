package mrthomas20121.tinkers_reforged.modifiers;

import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;

public class TokenMod extends Modifier {

    public TokenMod()
    {
        super("token");
        this.addAspects(new ModifierAspect.SingleAspect(this));
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public void updateNBT(NBTTagCompound modifierTag) {
        // same as level aspect, but we don't have a restriction here
        ModifierNBT data = ModifierNBT.readTag(modifierTag);
        data.level++;
        data.write(modifierTag);
    }

    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        // substract the modifiers
        NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
        ModifierNBT data = ModifierNBT.readTag(modifierTag);
        int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + data.level;
        toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
    }
}
