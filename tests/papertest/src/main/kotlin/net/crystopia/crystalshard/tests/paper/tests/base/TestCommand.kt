package net.crystopia.crystalshard.tests.paper.tests.base

import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.literalArgument
import net.crystopia.crystalshard.tests.paper.tests.new.*

object TestCommand {

    val command = commandTree("test") {
        literalArgument("plugin-messaging") {
            anyExecutor { sender, arguments ->
                PluginMessaging("plugin-messaging", sender, arguments).command()
            }
        }

        literalArgument("AdvancementTest") {
            anyExecutor { sender, arguments ->
                AdvancementTest("AdvancementTest", sender, arguments).command()
            }
        }

        literalArgument("ApplyCooldownTest") {
            anyExecutor { sender, arguments ->
                ApplyCooldownTest("ApplyCooldownTest", sender, arguments).command()
            }
        }

        literalArgument("ApplyMobEffectTest") {
            anyExecutor { sender, arguments ->
                ApplyMobEffectTest("ApplyMobEffectTest", sender, arguments).command()
            }
        }

        literalArgument("ClientModTest") {
            anyExecutor { sender, arguments ->
                ClientModTest("ClientModTest", sender, arguments).command()
            }
        }

        literalArgument("CloseContainerTest") {
            anyExecutor { sender, arguments ->
                CloseContainerTest("CloseContainerTest", sender, arguments).command()
            }
        }

        literalArgument("CreateAnimationTest") {
            anyExecutor { sender, arguments ->
                CreateAnimationTest("CreateAnimationTest", sender, arguments).command()
            }
        }

        literalArgument("CreateBlockDestroyStageTest") {
            anyExecutor { sender, arguments ->
                CreateBlockDestroyStageTest("CreateBlockDestroyStageTest", sender, arguments).command()
            }
        }

        literalArgument("CreateBlockEntityDataTest") {
            anyExecutor { sender, arguments ->
                CreateBlockEntityDataTest("CreateBlockEntityDataTest", sender, arguments).command()
            }
        }

        literalArgument("CreateBlockUpdateTest") {
            anyExecutor { sender, arguments ->
                CreateBlockUpdateTest("CreateBlockUpdateTest", sender, arguments).command()
            }
        }

        literalArgument("CreateEquipmentTest") {
            anyExecutor { sender, arguments ->
                CreateEquipmentTest("CreateEquipmentTest", sender, arguments).command()
            }
        }

        literalArgument("CreateOpenSignEditorTest") {
            anyExecutor { sender, arguments ->
                CreateOpenSignEditorTest("CreateOpenSignEditorTest", sender, arguments).command()
            }
        }

        literalArgument("DamageEventTest") {
            anyExecutor { sender, arguments ->
                DamageEventTest("DamageEventTest", sender, arguments).command()
            }
        }

        literalArgument("DialogTest") {
            anyExecutor { sender, arguments ->
                DialogTest("DialogTest", sender, arguments).command()
            }
        }

        literalArgument("InitWorldBorderTest") {
            anyExecutor { sender, arguments ->
                InitWorldBorderTest("InitWorldBorderTest", sender, arguments).command()
            }
        }

        literalArgument("MoveEntityTest") {
            anyExecutor { sender, arguments ->
                MoveEntityTest("MoveEntityTest", sender, arguments).command()
            }
        }

        literalArgument("MoveMinecartTest") {
            anyExecutor { sender, arguments ->
                MoveMinecartTest("MoveMinecartTest", sender, arguments).command()
            }
        }

        literalArgument("MoveVehicleTest") {
            anyExecutor { sender, arguments ->
                MoveVehicleTest("MoveVehicleTest", sender, arguments).command()
            }
        }

        literalArgument("NPCTest") {
            anyExecutor { sender, arguments ->
                NPCTest("NPCTest", sender, arguments).command()
            }
        }

        literalArgument("OpenScreenTest") {
            anyExecutor { sender, arguments ->
                OpenScreenTest("OpenScreenTest", sender, arguments).command()
            }
        }

        literalArgument("PacketDialogTest") {
            anyExecutor { sender, arguments ->
                PacketDialogTest("PacketDialogTest", sender, arguments).command()
            }
        }

        literalArgument("PacketGUITest") {
            anyExecutor { sender, arguments ->
                PacketGUITest("PacketGUITest", sender, arguments).command()
            }
        }

        literalArgument("PlayerHeadTest") {
            anyExecutor { sender, arguments ->
                PlayerHeadTest("PlayerHeadTest", sender, arguments).command()
            }
        }

        literalArgument("PlayerInfoRemoveTest") {
            anyExecutor { sender, arguments ->
                PlayerInfoRemoveTest("PlayerInfoRemoveTest", sender, arguments).command()
            }
        }

        literalArgument("PlayerInfoRemoveTest") {
            anyExecutor { sender, arguments ->
                PlayerInfoUpdateTest("PlayerInfoRemoveTest", sender, arguments).command()
            }
        }

        literalArgument("RemoveEntitiesTest") {
            anyExecutor { sender, arguments ->
                RemoveEntitiesTest("RemoveEntitiesTest", sender, arguments).command()
            }
        }

        literalArgument("RemoveMobEffectTest") {
            anyExecutor { sender, arguments ->
                RemoveMobEffectTest("RemoveMobEffectTest", sender, arguments).command()
            }
        }

        literalArgument("RotateHeadTest") {
            anyExecutor { sender, arguments ->
                RotateHeadTest("RotateHeadTest", sender, arguments).command()
            }
        }

        literalArgument("RunGameEventTest") {
            anyExecutor { sender, arguments ->
                RunGameEventTest("RunGameEventTest", sender, arguments).command()
            }
        }

        literalArgument("SpawnParticleTest") {
            anyExecutor { sender, arguments ->
                SpawnParticleTest("SpawnParticleTest", sender, arguments).command()
            }
        }

        literalArgument("WaypointTest") {
            anyExecutor { sender, arguments ->
                WaypointTest("WaypointTest", sender, arguments).command()
            }
        }

        literalArgument("WorldTest") {
            anyExecutor { sender, arguments ->
                WorldTest("WorldTest", sender, arguments).command()
            }
        }

        literalArgument("SendEntityEventTest") {
            anyExecutor { sender, arguments ->
                SendEntityEventTest("SendEntityEventTest", sender, arguments).command()
            }
        }

        literalArgument("SendObjectiveUpdateTest") {
            anyExecutor { sender, arguments ->
                SendObjectiveUpdateTest("SendObjectiveUpdateTest", sender, arguments).command()
            }
        }

        literalArgument("SendPlayerCombatKillTest") {
            anyExecutor { sender, arguments ->
                SendPlayerCombatKillTest("SendPlayerCombatKillTest", sender, arguments).command()
            }
        }

        literalArgument("SendTeamTest") {
            anyExecutor { sender, arguments ->
                SendTeamTest("SendTeamTest", sender, arguments).command()
            }
        }

        literalArgument("SendWorldEventTest") {
            anyExecutor { sender, arguments ->
                SendWorldEventTest("SendWorldEventTest", sender, arguments).command()
            }
        }

        literalArgument("SetContainerContentTest") {
            anyExecutor { sender, arguments ->
                SetContainerContentTest("SetContainerContentTest", sender, arguments).command()
            }
        }

        literalArgument("SetContainerDataTest") {
            anyExecutor { sender, arguments ->
                SetContainerDataTest("SetContainerDataTest", sender, arguments).command()
            }
        }

        literalArgument("SetContainerSlotTest") {
            anyExecutor { sender, arguments ->
                SetContainerSlotTest("SetContainerSlotTest", sender, arguments).command()
            }
        }

        literalArgument("SetDefaultSpawnPositionTest") {
            anyExecutor { sender, arguments ->
                SetDefaultSpawnPositionTest("SetDefaultSpawnPositionTest", sender, arguments).command()
            }
        }

        literalArgument("SetDisplayObjectiveTest") {
            anyExecutor { sender, arguments ->
                SetDisplayObjectiveTest("SetDisplayObjectiveTest", sender, arguments).command()
            }
        }

        literalArgument("SetEntityDataTest") {
            anyExecutor { sender, arguments ->
                SetEntityDataTest("SetEntityDataTest", sender, arguments).command()
            }
        }

        literalArgument("SetHealthTest") {
            anyExecutor { sender, arguments ->
                SetHealthTest("SetHealthTest", sender, arguments).command()
            }
        }

        literalArgument("SetItemOnCursorTest") {
            anyExecutor { sender, arguments ->
                SetItemOnCursorTest("SetItemOnCursorTest", sender, arguments).command()
            }
        }

        literalArgument("SetMapItemDataTest") {
            anyExecutor { sender, arguments ->
                SetMapItemDataTest("SetMapItemDataTest", sender, arguments).command()
            }
        }

        literalArgument("SetMerchantOfferTest") {
            anyExecutor { sender, arguments ->
                SetMerchantOfferTest("SetMerchantOfferTest", sender, arguments).command()
            }
        }

        literalArgument("SetPassengersTest") {
            anyExecutor { sender, arguments ->
                SetPassengersTest("SetPassengersTest", sender, arguments).command()
            }
        }

        literalArgument("SetScoreInDisplayObjectTest") {
            anyExecutor { sender, arguments ->
                SetScoreInDisplayObjectTest("SetScoreInDisplayObjectTest", sender, arguments).command()
            }
        }

        literalArgument("ResetScoreInDisplayObjectTest") {
            anyExecutor { sender, arguments ->
                ResetScoreInDisplayObjectTest("ResetScoreInDisplayObjectTest", sender, arguments).command()
            }
        }

        literalArgument("SetTabListTest") {
            anyExecutor { sender, arguments ->
                SetTabListTest("SetTabListTest", sender, arguments).command()
            }
        }

        literalArgument("SetTimeTest") {
            anyExecutor { sender, arguments ->
                SetTimeTest("SetTimeTest", sender, arguments).command()
            }
        }

        literalArgument("SetWorldBorderCenterTest") {
            anyExecutor { sender, arguments ->
                SetWorldBorderCenterTest("SetWorldBorderCenterTest", sender, arguments).command()
            }
        }

        literalArgument("SetWorldBorderLerpSizeTest") {
            anyExecutor { sender, arguments ->
                SetWorldBorderLerpSizeTest("SetWorldBorderLerpSizeTest", sender, arguments).command()
            }
        }

        literalArgument("SetWorldBorderSizeTest") {
            anyExecutor { sender, arguments ->
                SetWorldBorderSizeTest("SetWorldBorderSizeTest", sender, arguments).command()
            }
        }

        literalArgument("SetWorldBorderWarningDelayTest") {
            anyExecutor { sender, arguments ->
                SetWorldBorderWarningDelayTest("SetWorldBorderWarningDelayTest", sender, arguments).command()
            }
        }

        literalArgument("SetWorldBorderWarningDistanceTest") {
            anyExecutor { sender, arguments ->
                SetWorldBorderWarningDistanceTest("SetWorldBorderWarningDistanceTest", sender, arguments).command()
            }
        }

        literalArgument("TakeItemTest") {
            anyExecutor { sender, arguments ->
                TakeItemTest("TakeItemTest", sender, arguments).command()
            }
        }

        literalArgument("TeleportEntityTest") {
            anyExecutor { sender, arguments ->
                TeleportEntityTest("TeleportEntityTest", sender, arguments).command()
            }
        }

        literalArgument("UpdateAttributesTest") {
            anyExecutor { sender, arguments ->
                UpdateAttributesTest("UpdateAttributesTest", sender, arguments).command()
            }
        }

        literalArgument("UpdateEntityPositionSyncTest") {
            anyExecutor { sender, arguments ->
                UpdateEntityPositionSyncTest("UpdateEntityPositionSyncTest", sender, arguments).command()
            }
        }

        literalArgument("UpdatePlayerLookAtTest") {
            anyExecutor { sender, arguments ->
                UpdatePlayerLookAtTest("UpdatePlayerLookAtTest", sender, arguments).command()
            }
        }

        literalArgument("UpdatePlayerPositionTest") {
            anyExecutor { sender, arguments ->
                UpdatePlayerPositionTest("UpdatePlayerPositionTest", sender, arguments).command()
            }
        }

        literalArgument("UpdatePlayerRotationTest") {
            anyExecutor { sender, arguments ->
                UpdatePlayerRotationTest("UpdatePlayerRotationTest", sender, arguments).command()
            }
        }

        literalArgument("UpdateTickingStateTest") {
            anyExecutor { sender, arguments ->
                UpdateTickingStateTest("UpdateTickingStateTest", sender, arguments).command()
            }
        }

        literalArgument("UpdateTickingStepTest") {
            anyExecutor { sender, arguments ->
                UpdateTickingStepTest("UpdateTickingStepTest", sender, arguments).command()
            }
        }
    }

}