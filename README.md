# BetterCookie Mod

![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)
![NeoForge](https://img.shields.io/badge/NeoForge-21.1.218-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)
![Version](https://img.shields.io/badge/Version-1.0.0-lightgrey.svg)

## 📖 简介 / Introduction

**English** | [**中文**](#中文)

A lightweight Minecraft 1.21.1 mod based on NeoForge v21.1.218 that enhances the storage efficiency and quality of life for cookies in-game. This mod adds new blocks and items to improve the cookie experience while maintaining the vanilla feel.

### ✨ 主要特性 / Key Features

- **曲奇块 (Cookie Block)**: Compact storage solution for cookies
- **曲奇碎 (Cookie Pieces)**: New food item with unique mechanics
- **无序合成配方 (Shapeless Recipes)**:
  - 64 Cookie Pieces = 8 Cookies
  - 8 Cookies = 1 Cookie Block
  - 1 Cookie Block = 8 Cookies
- **优化的进食机制**: Improved cookie consumption experience
- **完整本地化**: Supports both English and Chinese languages
- **轻量化设计**: Minimal performance impact vanilla-friendly

---

## 🚀 安装 / Installation

### 要求 / Requirements
- **Minecraft**: 1.21.1
- **NeoForge**: 21.1.218 或兼容版本 / or compatible version

### 安装步骤 / Steps
1. 安装 [NeoForge](https://neoforged.net/) 21.1.218
2. 下载最新版本的 BetterCookie Mod
3. 将 `.jar` 文件放入 `mods` 文件夹
4. 启动游戏

---

## 🍪 使用方法 / Usage

### 合成配方 / Crafting Recipes

| 合成 / Crafting | 材料 / Materials | 产出 / Output |
|----------------|-----------------|--------------|
| 曲奇块 / Cookie Block | 8 曲奇 / 8 Cookies | 1 曲奇块 / 1 Cookie Block |
| 曲奇碎 / Cookie Pieces | 1 曲奇 / 1 Cookie | 8 曲奇碎 / 8 Cookie Pieces |
| 曲奇 / Cookies | 64 曲奇碎 / 64 Cookie Pieces | 8 曲奇 / 8 Cookies |

### 游戏内功能 / In-Game Features
- **右键点击曲奇块**可分解为8个曲奇
- **曲奇碎**提供快速恢复饥饿值的零食
- 所有配方均为无序合成可在工作台中任意摆放
- 完全兼容原版游戏机制

---

## 🗂️ 项目结构 / Project Structure
```

bettercookie_mod-1.0.0-1.21.1.jar
├── META-INF/
│   └── mods.toml# 模组配置文件
├── assets/bettercookie/
│   ├── lang/# 本地化文件
│   │   ├── en_us.json  # 英文翻译
│   │   └── zh_cn.json  # 中文翻译
│   ├── models/  # 3D模型
│   ├── textures/# 材质贴图
│   └── sounds/  # 音效文件
├── data/bettercookie/
│   ├── recipes/ # 合成配方
│   └── tags/# 标签定义
└── com/hainabaichuan1019/bettercookies/
└── *.java   # 源代码文件

```text
---

## 🛠️ 开发指南 / Development Guide

### 环境搭建 / Setup Environment
1. 克隆此仓库
2. 确保已安装 JDK 21 或更高版本
3. 配置 NeoForge MDK 开发环境

### 构建指令 / Build Commands
```bash
# 清理项目
./gradlew clean

# 构建模组
./gradlew build

# 运行开发客户端
./gradlew runClient

# 运行开发服务器
./gradlew runServer
```

### 贡献指南 / Contributing

欢迎提交 Pull Request！请确保：
1. 代码风格与现有代码保持一致

2. 添加必要的注释

3. 更新相关文档

4. 测试功能正常运行

---

## 📄 许可证 / License

本项目采用 MIT 许可证-查看 [LICENSE](https://license/) 文件了解详情。

```text
MIT License

Copyright (c) 2024 hainabaichuan1019

Permission is hereby granted free of charge to any person obtaining a copy
of this software and associated documentation files (the \Software\) to deal
in the Software without restriction including without limitation the rights
to use copy modify merge publish distribute sublicense and/or sell
copies of the Software and to permit persons to whom the Software is
furnished to do so subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED \AS IS\ WITHOUT WARRANTY OF ANY KIND EXPRESS OR
IMPLIED INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM DAMAGES OR OTHER
LIABILITY WHETHER IN AN ACTION OF CONTRACT TORT OR OTHERWISE ARISING FROM
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 🙏 致谢 / Credits

### 作者 / Author
- **hainabaichuan1019** -项目创建者和主要开发者

### 特别感谢 / Special Thanks
- **NeoForge 团队** -提供强大的模组加载器

- **Minecraft 社区** -灵感和支持

- **所有测试者** -反馈和错误报告

### 翻译贡献 / Translation Credits
- 英文(en_us): hainabaichuan1019

- 中文(zh_cn): hainabaichuan1019

---

## 📞 联系方式 / Contact
- **GitHub**: [hainabaichuan1019](https://github.com/hainabaichuan1019)

- **模组页面**: [Modrinth](https://modrinth.com/) / [CurseForge](https://curseforge.com/)

- **问题反馈**: 请在GitHub仓库提交Issue

---

## 🔄 更新日志 / Changelog

### v1.0.0(2026-01-19)
- 初始发布版本

- 添加曲奇块和曲奇碎

- 实现所有合成配方

- 支持英文和中文本地化

- 基于NeoForge 21.1.218构建

---

# 中文

## 📖 简介

基于 NeoForge v21.1.218 的轻量级 Minecraft 1.21.1 模组旨在提高游戏中曲奇的存储效率和生活质量。本模组添加了新的方块和物品来改善曲奇体验同时保持原版游戏的感觉。

## ✨ 功能特性
- **曲奇块**: 曲奇的紧凑存储方案节省背包空间

- **曲奇碎**: 新的食物物品提供独特的食用机制

- **无序合成配方**:
- 64 曲奇碎 = 8 曲奇

- 8 曲奇 = 1 曲奇块

- 1 曲奇块 = 8 曲奇


- **优化的进食机制**: 改进的曲奇食用体验

- **完整本地化**: 支持英文和中文语言

- **轻量化设计**: 对性能影响极小尊重原版体验

## 🚀 安装方法

### 要求
- **Minecraft**: 1.21.1

- **NeoForge**: 21.1.218 或兼容版本

### 步骤
1. 安装 [NeoForge](https://neoforged.net/) 21.1.218

2. 下载最新版本的 BetterCookie 模组

3. 将 `.jar` 文件放入 `mods` 文件夹

4. 启动游戏

## 🍪 使用方法

### 合成配方
| 合成 | 材料 | 产出 |
| --- | --- | --- |
| 曲奇块 | 8 曲奇 | 1 曲奇块 |
| 曲奇碎 | 1 曲奇 | 8 曲奇碎 |
| 曲奇 | 64 曲奇碎 | 8 曲奇 |

### 游戏内功能
- **右键点击曲奇块** 可分解为8个曲奇

- **曲奇碎** 提供快速恢复饥饿值的零食

- 所有配方均为无序合成可在工作台中任意摆放

- 完全兼容原版游戏机制

## 🛠️ 开发信息

### 技术细节
- **开发工具**: NeoForge MDK

- **Java版本**: 21

- **构建工具**: Gradle

- **许可证**: MIT

### 构建指令

```bash
# 清理项目
./gradlew clean

# 构建模组
./gradlew build
```

## 📄 许可证

本项目采用 MIT 许可证-查看 [LICENSE](https://license/) 文件了解详情。

## 🙏 致谢

感谢所有支持本项目的玩家和开发者！

---

**注意**: 本模组为开源项目欢迎贡献代码和反馈建议。使用过程中如遇问题请在GitHub仓库提交Issue。

祝你游戏愉快！🍪✨

```text
将这个内容保存为 `README.md` 文件放在你的项目根目录即可。
