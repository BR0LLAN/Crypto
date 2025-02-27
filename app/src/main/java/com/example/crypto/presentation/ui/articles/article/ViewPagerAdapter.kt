package com.example.crypto.presentation.ui.articles.article

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devcraft.cryptoapp.R

class ViewPagerAdapter(activity: FragmentActivity, articleId: Int) : FragmentStateAdapter(activity) {
    private var title: Array<String> = getTitle(articleId)
    private var textContent: Array<String> = getContent(articleId)
    private var imgResource: Array<Int> = getImg(articleId)

    private fun getTitle(articleId: Int): Array<String> {
        when(articleId) {
            1 -> return arrayOf(
                "What is cryptocurrency?",
                "What is Bitcoin?",
                "What is Ethereum?",
                "What is blockchain?"
            )
            2 -> return arrayOf(
                "How to Start Trading Bitcoin?"
            )
            3 -> return arrayOf(
                "3 Trading Strategies You Should" +
                        "Consider Using In" +
                        "The Crypto Market",
                "Arbitrage",
                "Fundamental Analysis",
                "Swing Trading"
            )
        }
        return arrayOf()
    }

    private fun getImg(articleId: Int): Array<Int>{
        when(articleId){
            1-> return arrayOf(
                R.drawable.ic_articleonefirst,
                R.drawable.ic_articleonesecond,
                R.drawable.ic_articleonethird,
                R.drawable.ic_articleonefourth
            )
            2-> return arrayOf(
                R.drawable.ic_articletwofirst
            )
            3-> return arrayOf(
                R.drawable.ic_articlethreefirst,
                R.drawable.ic_articlethreesecond,
                R.drawable.ic_articlethreethird,
                R.drawable.ic_articlethreefourth
            )
        }
        return arrayOf()
    }

    private fun getContent(articleId: Int): Array<String>{
        when(articleId){
            1->return arrayOf(
                "Cryptocurrency is a form of payment that can be exchanged online for goods and services. " +
                        "Many companies have issued their own currencies, often called tokens, and these can be traded specifically for the good or service that the company provides." +
                        " Think of them as you would arcade tokens or casino chips. You’ll need to exchange real currency for the cryptocurrency to access the good or service.\n" +
                        "\n" +
                        "Cryptocurrencies work using a technology called blockchain." +
                        " Blockchain is a decentralized technology spread across many computers that manages and records transactions." +
                        " Part of the appeal of this technology is its security.",

                "Bitcoin is a digital currency created in January 2009. It follows the ideas set out in a whitepaper by the mysterious and pseudonymous Satoshi Nakamoto. " +
                        "The identity of the person or persons who created the technology is still a mystery. " +
                        "Bitcoin offers the promise of lower transaction fees than traditional online payment mechanisms and, unlike government-issued currencies, it is operated by a decentralized authority.\n" +
                        "\n" +
                        "Bitcoin is a type of cryptocurrency. There is no physical bitcoin, only balances kept on a public ledger that everyone has transparent access to." +
                        " All bitcoin transactions are verified by a massive amount of computing power. " +
                        "Bitcoin is not issued or backed by any banks or governments, nor is an individual bitcoin valuable as a commodity. " +
                        "Despite it not being legal tender in most parts of the world, bitcoin is very popular and has triggered the launch of hundreds of other cryptocurrencies, collectively referred to as altcoins. " +
                        "Bitcoin is commonly abbreviated as \"BTC.\"",

                "Ethereum is a digital platform which allows people to build a range of decentralised applications.\n" +
                        "\n" +
                        "These applications can include security programs, voting systems and methods of payment. " +
                        "Like bitcoin, ethereum operates outside the mandate of central authorities such as banks and governments.\n" +
                        "\n" +
                        "The idea behind ethereum was created by Vitalik Buterin. He launched the first version of the platform in 2015, with the help of several co-founders. " +
                        "Since then it has grown rapidly in popularity and has helped prompt an increase of new rivals to bitcoin.",

                "At its core, blockchain is a distributed digital ledger that stores data of any kind. " +
                        "A blockchain can record information about cryptocurrency transactions, NFT ownership or DeFi smart contracts.\n" +
                        "\n" +
                        "While any conventional database can store this sort of information, blockchain is unique in that it’s totally decentralized. " +
                        "Rather than being maintained in one location, by a centralized administrator—think of an Excel spreadsheet or a bank database—many identical copies of a blockchain database are held on multiple computers spread out across a network. " +
                        "These individual computers are referred to as nodes."
            )
            2-> return arrayOf(
                "The first thing you need to get started trading bitcoin is to open a bitcoin wallet. If you do not have a bitcoin wallet then you can open one at the biggest wallet called Coinbase. We have arranged a special deal for everyone wanting to get started in bitcoin to get a free \$10 at Coinbase. Get your free \$10 by opening your Coinbase account here.\n" +
                        "\n" +
                        "Bitcoin traders are actively seeking the best possible solutions for trading and investing in bitcoin. We have some of the best methods explained right here in this article. We have learned this bitcoin wisdom by trial and error and we are going to show you what is working right now. The methods we teach are not dependent on the price of bitcoin. They can be used whether bitcoin is going up or going down.\n" +
                        "\n" +
                        "Keep in mind that it is possible to lose money. Your capital is at risk while trading cryptocurrency because it is still trading at the end of the day. We always recommend that you demo trade before risking any live money. Also, read the trading volume guide.\n" +
                        "\n" +
                        "These bitcoin strategies can also be used for trading bitcoin cash as well as other cryptocurrencies. " +
                        "In fact, you can use this as a trade guide for any type of trading instrument. " +
                        "Blockchain technology is a big step forward for how to access information. " +
                        "Many companies are starting to develop applications to use Blockchain in their favor. " +
                        "Remember that when trading digital currency, it may seem like it's not a real currency. " +
                        "But it actually is real. This is not some Ponzi scheme. " +
                        "Before you buy bitcoins, have a solid plan in place and don’t underestimate the cryptocurrency markets. " +
                        "You must do your technical analysis just as if you were going to day trade any other instruments. " +
                        "You can also read our best Gann Fan trading strategy."
            )
            3->return arrayOf(
                "You will see that the arbitrage trading strategy is simple and conceptually very straightforward. In simple words, it is when you buy an asset when the price is low and sell it when the price goes higher. People have been simultaneously buying and selling cryptocurrencies and using the volatility in the crypto market in order to make profits.\n" +
                        "\n" +
                        "For example, arbitrage trading in bitcoin’s market would be when you buy bitcoin for €10,000 on one cryptocurrency exchange, and then later sell it on another cryptocurrency exchange platform for €11,000. In this example, the trader will be able to make a €1,000 profit by simply buying and selling the cryptocurrency. It is important to note that this is just an example and in the real world a price differential of €1,000 will probably not exist between cryptocurrency exchanges.",

                "You will see that the arbitrage trading strategy is simple and conceptually very straightforward. In simple words, it is when you buy an asset when the price is low and sell it when the price goes higher. People have been simultaneously buying and selling cryptocurrencies and using the volatility in the crypto market in order to make profits.\n" +
                        "\n" +
                        "For example, arbitrage trading in bitcoin’s market would be when you buy bitcoin for €10,000 on one cryptocurrency exchange, and then later sell it on another cryptocurrency exchange platform for €11,000. In this example, the trader will be able to make a €1,000 profit by simply buying and selling the cryptocurrency. It is important to note that this is just an example and in the real world a price differential of €1,000 will probably not exist between cryptocurrency exchanges.",

                "People have been using fundamental analysis as a trading strategy for a very long time. This trading strategy applies regularly in the context of traditional asset classes such as bonds and stocks. Bitcoin traders will use a number of different indicators in order to identify if an asset is undervalued or overvalued. It is important to take into account many different indicators and factors to properly use fundamental analysis.\n" +
                        "\n" +
                        "It may prove to be a little difficult for anyone to apply fundamental analysis in the context of the cryptocurrency market or the bitcoin market because these digital currencies are not underpinned by companies but decentralized networks. It may be a little difficult but it will not be entirely impossible. The fundamental analysis trading strategy will mostly be used by investors and traders who want to hold their assets for longer periods of time. The strategy is based on the idea that if an asset is truly undervalued, then it should increase its price and value over time. The volatility of the crypto market should not concern a trader or an investor who is looking to hold an asset for a long period of time.",

                "This method of trading cryptocurrencies is the exact opposite of fundamental analysis trading strategy. Traders will use the swing in the price changes to their advantage. The volatility of the crypto market is a blessing for any trader who can use this strategy successfully. These traders will typically only hold their assets for a short term. When trading in the crypto market, swing trading should prove to be very effective given the market’s volatility.\n" +
                        "\n" +
                        "The traders will have to carefully time the market, which might be a little difficult due to the unpredictable nature of the crypto world. Executing trades at the right time will be very important when using this trading strategy. Performing trades manually will probably be limited, as anyone can make a mistake when performing trades. No human being can sit in front of a computer screen for days waiting for the right time to perform a trade. Automated trading through trading bots and automated trading platforms, like the official Bitcoin Rush, have proven to be very helpful and allows the traders to use the swing trading strategy successfully.\n" +
                        "\n" +
                        "The trading strategies that we have talked about are just a few, very famous ones that anyone can use when trading cryptocurrencies. Please do not forget that there are several other strategies that you could use. It is important to choose the right strategy that meets all of your requirements and works for you."
            )
        }
        return arrayOf()
    }


    override fun getItemCount(): Int {
        return title.size
    }

    override fun createFragment(position: Int): Fragment = ArticleFragment().apply {
        arguments = bundleOf(
            "title" to title[position],
            "textContent" to textContent[position],
            "img_resource" to imgResource[position]
        )}
}