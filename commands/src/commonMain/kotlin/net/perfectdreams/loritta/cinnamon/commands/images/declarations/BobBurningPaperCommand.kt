package net.perfectdreams.loritta.cinnamon.commands.images.declarations

import net.perfectdreams.loritta.cinnamon.commands.images.BobBurningPaperExecutor
import net.perfectdreams.loritta.cinnamon.common.commands.CommandCategory
import net.perfectdreams.loritta.cinnamon.common.commands.declarations.CommandDeclaration
import net.perfectdreams.loritta.cinnamon.i18n.I18nKeysData

object BobBurningPaperCommand : CommandDeclaration {
    val I18N_PREFIX = I18nKeysData.Commands.Command.Bobburningpaper

    override fun declaration() = command(listOf("bobburningpaper", "bobpaperfire", "bobfire", "bobpapelfogo", "bobfogo"), CommandCategory.IMAGES, I18N_PREFIX.Description) {
        executor = BobBurningPaperExecutor
    }
}