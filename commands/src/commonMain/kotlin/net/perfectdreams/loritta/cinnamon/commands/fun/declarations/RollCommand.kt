package net.perfectdreams.loritta.cinnamon.commands.`fun`.declarations

import net.perfectdreams.loritta.cinnamon.commands.`fun`.RollExecutor
import net.perfectdreams.loritta.cinnamon.common.commands.CommandCategory
import net.perfectdreams.loritta.cinnamon.common.commands.declarations.CommandDeclaration
import net.perfectdreams.loritta.cinnamon.i18n.I18nKeysData

object RollCommand : CommandDeclaration {
    val I18N_PREFIX = I18nKeysData.Commands.Command.Roll

    override fun declaration() = command(listOf("roll"), CommandCategory.FUN, I18N_PREFIX.Description) {
        executor = RollExecutor
    }
}