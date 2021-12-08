package net.perfectdreams.loritta.cinnamon.platform.commands.images.declarations

import net.perfectdreams.loritta.cinnamon.platform.commands.images.PetPetExecutor
import net.perfectdreams.loritta.cinnamon.platform.commands.CommandCategory
import net.perfectdreams.loritta.cinnamon.platform.commands.declarations.CommandDeclaration
import net.perfectdreams.loritta.cinnamon.i18n.I18nKeysData

object PetPetCommand : CommandDeclaration {
    val I18N_PREFIX = I18nKeysData.Commands.Command.Petpet

    override fun declaration() = command(listOf("petpet"), CommandCategory.IMAGES, I18N_PREFIX.Description) {
        executor = PetPetExecutor
    }
}