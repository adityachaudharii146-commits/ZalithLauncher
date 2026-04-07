package com.movtery.zalithlauncher.utils

import android.content.Context
import com.movtery.zalithlauncher.feature.customprofilepath.ProfilePathHome
import com.movtery.zalithlauncher.utils.path.PathManager
import net.kdt.pojavlaunch.Tools
import java.io.File
import java.io.IOException

class CopyDefaultFromAssets {
    companion object {
        @JvmStatic
        @Throws(IOException::class)
        fun copyFromAssets(context: Context?) {
            // 默认控制布局
            if (checkDirectoryEmpty(PathManager.DIR_CTRLMAP_PATH)) {
                Tools.copyAssetFile(context, "default.json", PathManager.DIR_CTRLMAP_PATH, false)
            }

            // 内置 Arch MC 自定义版本配置
            val archMcVersionDir = File(ProfilePathHome.getVersionsHome(), "archmc-1.21.11")
            val archMcVersionJson = File(archMcVersionDir, "archmc-1.21.11.json")
            if (!archMcVersionJson.exists()) {
                Tools.copyAssetFile(context, "versions/archmc-1.21.11/archmc-1.21.11.json", archMcVersionDir.absolutePath, false)
            }
        }

        private fun checkDirectoryEmpty(dir: String?): Boolean {
            val controlDir = dir?.let { File(it) }
            val files = controlDir?.listFiles()
            return files?.isEmpty() ?: true
        }
    }
}
