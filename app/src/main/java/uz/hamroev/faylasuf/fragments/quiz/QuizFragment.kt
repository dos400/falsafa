package uz.hamroev.faylasuf.fragments.quiz

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.hamroev.faylasuf.R
import uz.hamroev.faylasuf.database.QuizDatabase
import uz.hamroev.faylasuf.database.userDatabase.UserDatabase
import uz.hamroev.faylasuf.databinding.DialogContinueBinding
import uz.hamroev.faylasuf.databinding.DialogQuitBinding
import uz.hamroev.faylasuf.databinding.FragmentQuizBinding
import uz.hamroev.faylasuf.utils.*
import java.text.SimpleDateFormat
import java.util.*

class QuizFragment : Fragment() {


    private lateinit var binding: FragmentQuizBinding

    var isSelect: Boolean = false
    var questionId = 0
    var whichRadioButtonClick = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)


        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        loadQuiz()

        loadUserData()

        binding.backButton.setOnClickListener {
            isUserWantQuit()
        }
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                isUserWantQuit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), onBackPressedCallback)


//        val quiz = QuizDatabase.GET.getQuizDatabase().getQuizDao().getQuiz(1)
//        binding.questionTv.text = quiz[0].question
//        Log.d("AAAA", "onCreateView: ${quiz.get(0).question}")
//
//
//        val savedUserAnswers = SavedUserAnswers(0, getCurrentDateAndTime())
//        UserDatabase.GET.getUserDatabase().getSavedUserAnswerDao().creteNewUserAnswer(savedUserAnswers)
//        val savedId = UserDatabase.GET.getUserDatabase().getSavedUserAnswerDao().getSavedId()
//        toast(savedId.toString())


        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.variant_a -> {
                    isSelect = true
                    binding.check.setBackgroundResource(R.drawable.back_next)
                }
                R.id.variant_b -> {
                    isSelect = true
                    binding.check.setBackgroundResource(R.drawable.back_next)
                }
                R.id.variant_c -> {
                    isSelect = true
                    binding.check.setBackgroundResource(R.drawable.back_next)
                }
                R.id.variant_d -> {
                    isSelect = true
                    binding.check.setBackgroundResource(R.drawable.back_next)
                }
            }
        }



        binding.check.setOnClickListener {
            if (isSelect) {
                for (i in 0 until binding.radioGroup.childCount) {
                    val radioButton = binding.radioGroup.getChildAt(i) as RadioButton
                    if (radioButton.isChecked) {
                        // viewModel.checkAnswer(answer = radioButton.text.toString())
                        whichRadioButtonClick = i
                        checkAnswer(radioButton.text.toString())
                    }
                }
            } else {
                requireContext().vibrate(200)
                toast("Javoblardan birini tanlang!")
            }

        }











        return binding.root
    }


    private fun checkAnswer(answer: String) {
        val answersList = QuizDatabase.GET.getQuizDatabase().getQuizDao().getAnswersOfQuestion(questionId)
        for (answersEntity in answersList) {
            if (answersEntity.answer == answer) {
                if (answersEntity.correct == 1) {
                    toast("to'g'ri")
                    QuizDatabase.GET.getQuizDatabase().getQuizDao().updateUsedQuestion(questionId)
                    val trueRadioButton = binding.radioGroup.getChildAt(whichRadioButtonClick) as RadioButton
                    trueRadioButton.setBackgroundResource(R.drawable.bg_variant_correct)
                    trueRadioButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                    trueRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.ic_correct
                        ), null, null, null
                    )

                } else {
                    toast("noto'g'ri")
                    QuizDatabase.GET.getQuizDatabase().getQuizDao().updateUsedQuestion(questionId)
                    val trueRadioButton = binding.radioGroup.getChildAt(whichRadioButtonClick) as RadioButton
                    trueRadioButton.setBackgroundResource(R.drawable.bg_variant_incorrect)
                    trueRadioButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                    trueRadioButton.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(
                            requireContext(),
                            R.drawable.ic_error_red
                        ), null, null, null
                    )

                }
            }
        }

        //global scope for only application class
        //in fragment we have lifecycleScope
        lifecycleScope.launch(Dispatchers.Main) {
            // Delay for 2 seconds
            binding.check.gone()
            delay(1500) // Delay time is in milliseconds
            // Code after the delay
            // For example, you might update the UI here


            if (hasNext()) {
                loadNext()
            } else {
                clearAll()
                loadNext()
            }
        }


    }

    private fun loadNext() {
        updateLevel()
        binding.check.visible()
        simpleClearRadioButtons()

        when (currentLevel()) {
            in 1..15 -> {
                getQuestion(difficulty = 1)
            }
            in 16..25 -> {
                getQuestion(difficulty = 2)
            }
            in 26..30 -> {
                getQuestion(difficulty = 3)
            }
            else -> {
                clearAll()
            }
        }

    }

    private fun simpleClearRadioButtons() {
        binding.radioGroup.clearCheck()

        isSelect = false
        binding.check.setBackgroundResource(R.drawable.back_default)


        binding.variantA.setBackgroundResource(R.drawable.bg_variant_default)
        binding.variantB.setBackgroundResource(R.drawable.bg_variant_default)
        binding.variantC.setBackgroundResource(R.drawable.bg_variant_default)
        binding.variantD.setBackgroundResource(R.drawable.bg_variant_default)

        binding.variantA.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.variantB.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.variantC.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.variantD.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

        binding.variantA.setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.ic_variant_a
            ), null, null, null
        )

        binding.variantB.setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.ic_variant_b
            ), null, null, null
        )

        binding.variantC.setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.ic_variant_c
            ), null, null, null
        )

        binding.variantD.setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.ic_variant_d
            ), null, null, null
        )


    }

    private fun updateLevel() {
        UserDatabase.GET.getUserDatabase().getUserDao().updateLevel(currentLevel())
        loadUserData()
    }


    private fun hasNext(): Boolean = currentLevel() < 30


    private fun loadUserData() {

        val currentCoin = UserDatabase.GET.getUserDatabase().getUserDao().getCurrentCoin()

        val currentLevel = UserDatabase.GET.getUserDatabase().getUserDao().getCurrentLevel()

        val hasReplaceQuiz = UserDatabase.GET.getUserDatabase().getUserDao().getHasReplaceQuiz()
        val hasHelpAnswer = UserDatabase.GET.getUserDatabase().getUserDao().getHasHelpAnswer()
        val hasHintAnswer = UserDatabase.GET.getUserDatabase().getUserDao().getHasHintAnswer()

        binding.apply {
            userCoinTv.text = currentCoin.toString().formatPrice()
            questionNumberTv.text = "${currentLevel}/30"
            progressView1.progress = currentLevel.toFloat()

            if (hasReplaceQuiz) {
                replaceIv.setImageResource(R.drawable.ic_used)
            } else {
                replaceIv.setImageResource(R.drawable.ic_refresh)
            }

            if (hasHelpAnswer) {
                helpIv.setImageResource(R.drawable.ic_used)
            } else {
                helpIv.setImageResource(R.drawable.ic_philosopher)
            }

            if (hasHintAnswer) {
                hintIv.setImageResource(R.drawable.ic_used)
            } else {
                hintIv.setImageResource(R.drawable.ic_hint)
            }

        }


    }

    private fun loadQuiz() {
        if (currentLevel() > 1) {
            showContinueDialog()
        } else {
            startNewGame()
        }

    }

    private fun startNewGame() {

        clearAll()

        loadUserData()
        loadQuestion(currentLevel())


    }

    private fun showContinueDialog() {
        // show dialog for game continue

        val alertDialog = android.app.AlertDialog.Builder(binding.root.context)
        val dialog = alertDialog.create()
        val bindingContinue =
            DialogContinueBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setView(bindingContinue.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)

        bindingContinue.yesButton.setOnClickListener {
            continueGame()
            dialog.dismiss()
        }

        bindingContinue.noButton.setOnClickListener {
            startNewGame()
            dialog.dismiss()
        }


        dialog.show()

    }

    private fun continueGame() {
        loadUserData()
        loadQuestion(currentLevel())
    }

    private fun loadQuestion(currentLevel: Int) {

        when (currentLevel) {
            in 1..15 -> {
                getQuestion(difficulty = 1)
            }
            in 16..25 -> {
                getQuestion(difficulty = 2)
            }
            in 26..30 -> {
                getQuestion(difficulty = 3)
            }
            else -> {
                clearAll()
            }
        }
    }

    private fun clearAll() {
        UserDatabase.GET.getUserDatabase().getUserDao().clearLevel()

        UserDatabase.GET.getUserDatabase().getUserDao().clearReplaceQuiz()
        UserDatabase.GET.getUserDatabase().getUserDao().clearHelpAnswer()
        UserDatabase.GET.getUserDatabase().getUserDao().clearHintAnswer()
    }

    private fun getQuestion(difficulty: Int) {
        val quiz = QuizDatabase.GET.getQuizDatabase().getQuizDao().getQuiz(difficulty)
        questionId = quiz[0].id
        val answersList = QuizDatabase.GET.getQuizDatabase().getQuizDao().getAnswersOfQuestion(questionId)
        // QuizDatabase.GET.getQuizDatabase().getQuizDao().updateUsedQuestion(questionId)

        binding.apply {
            questionTv.text = quiz[0].question.toString()
            variantA.text = answersList[0].answer
            variantB.text = answersList[1].answer
            variantC.text = answersList[2].answer
            variantD.text = answersList[3].answer
        }

    }

    private fun currentLevel(): Int {
        return UserDatabase.GET.getUserDatabase().getUserDao().getCurrentLevel()

    }

    private fun getCurrentDateAndTime(): String {
        val dateAndTime: Date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val date = dateFormat.format(dateAndTime)
        val time = timeFormat.format(dateAndTime)
        val vaqt: String = "$date - $time"
        //Toast.makeText(this, "$vaqt", Toast.LENGTH_SHORT).show()
        return vaqt

    }


    private fun isUserWantQuit() {
        // ask from user do you want to quit with dialog ?

        val alertDialog = android.app.AlertDialog.Builder(binding.root.context)
        val dialog = alertDialog.create()
        val bindingContinue =
            DialogQuitBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setView(bindingContinue.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)

        bindingContinue.yesButton.setOnClickListener {
            findNavController().popBackStack()
            dialog.dismiss()
        }

        bindingContinue.noButton.setOnClickListener {
            dialog.dismiss()
        }


        dialog.show()

    }


}


