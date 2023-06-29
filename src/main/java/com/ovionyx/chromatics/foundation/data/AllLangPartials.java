package com.ovionyx.chromatics.foundation.data;

import com.google.gson.JsonElement;
import com.ovionyx.chromatics.Chromatics;
import com.simibubi.create.foundation.data.LangPartial;
import com.simibubi.create.foundation.utility.Lang;

import java.util.function.Supplier;

public enum AllLangPartials implements LangPartial {
    INTERFACE("Chromatic Capabilities UI & Messages"),
    TOOLTIPS("Chromatic Capabilities Item Descriptions"),
    ;
    private final String displayName;
    private final Supplier<JsonElement> provider;

    private AllLangPartials(String displayName) {
        this.displayName = displayName;
        String fileName = Lang.asId(name());
        this.provider = () -> LangPartial.fromResource(Chromatics.MODID, fileName);
    }

    private AllLangPartials(String displayName, Supplier<JsonElement> provider) {
        this.displayName = displayName;
        this.provider = provider;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public JsonElement provide() {
        return provider.get();
    }
}
