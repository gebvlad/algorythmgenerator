package com.gebvlad.algorythmgenerator;

import org.antlr.runtime.*;

public class ProgramChecker {
    /**
     * Синтаксическая проверка программы на псевдокоде
     *
     * @param programm Программа на псевдокоде
     * @return false - ошибок нет<br>true - ошибки есть
     */
    public static boolean check(String programm) {

        try {
            ANTLRStringStream in = new ANTLRStringStream(programm);
            edapl_langLexer lexer = new edapl_langLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            edapl_langParser parser = new edapl_langParser(tokens);
            parser.algorythm();
            return lexer.check() | parser.check();
        } catch (Exception e) {
        }
        return false;
    }

    private static class edapl_langLexer extends Lexer {

        public static final int EOF = -1;
        public static final int T__4 = 4;
        public static final int T__5 = 5;
        public static final int T__6 = 6;
        public static final int T__7 = 7;
        public static final int T__8 = 8;
        public static final int T__9 = 9;
        public static final int T__10 = 10;
        public static final int T__11 = 11;
        public static final int T__12 = 12;
        public static final int T__13 = 13;
        public static final int T__14 = 14;
        public static final int T__15 = 15;
        public static final int T__16 = 16;
        public static final int T__17 = 17;
        public static final int T__18 = 18;
        public static final int T__19 = 19;
        public static final int T__20 = 20;
        public static final int T__21 = 21;
        public static final int T__22 = 22;
        public static final int T__23 = 23;
        public static final int T__24 = 24;
        public static final int T__25 = 25;
        public static final int T__26 = 26;
        static final String DFA1_eotS =
                "\7\uffff\1\27\20\uffff\1\34\4\uffff";
        static final String DFA1_eofS =
                "\35\uffff";
        static final String DFA1_minS =
                "\1\60\1\uffff\1\116\1\111\3\uffff\1\62\12\uffff\1\104\5\uffff\1"
                        + "\101\4\uffff";
        static final String DFA1_maxS =
                "\1\171\1\uffff\1\116\1\117\3\uffff\1\101\12\uffff\1\104\5\uffff"
                        + "\1\120\4\uffff";
        static final String DFA1_acceptS =
                "\1\uffff\1\1\2\uffff\1\4\1\5\1\6\1\uffff\1\16\1\17\1\20\1\21\1"
                        + "\22\1\23\1\24\1\25\1\26\1\27\1\uffff\1\3\1\14\1\10\1\12\1\11\1\uffff"
                        + "\1\7\1\13\1\15\1\2";
        static final String DFA1_specialS =
                "\35\uffff}>";
        // $ANTLR end "T__4"
        static final String[] DFA1_transitionS = {
                "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\7\uffff"
                        + "\1\5\1\1\2\uffff\1\2\6\uffff\1\3\3\uffff\1\7\47\uffff\1\6\1"
                        + "\4",
                "",
                "\1\22",
                "\1\23\5\uffff\1\24",
                "",
                "",
                "",
                "\1\26\16\uffff\1\25",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "\1\30",
                "",
                "",
                "",
                "",
                "",
                "\1\31\12\uffff\1\33\3\uffff\1\32",
                "",
                "",
                "",
                ""
        };
        // $ANTLR end "T__5"
        static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
        // $ANTLR end "T__6"
        static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
        // $ANTLR end "T__7"
        static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
        // $ANTLR end "T__8"
        static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
        // $ANTLR end "T__9"
        static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
        // $ANTLR end "T__10"
        static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
        // $ANTLR end "T__11"
        static final short[][] DFA1_transition;
        // $ANTLR end "T__12"

        static {
            int numStates = DFA1_transitionS.length;
            DFA1_transition = new short[numStates][];
            for (int i = 0; i < numStates; i++) {
                DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
            }
        }
        // $ANTLR end "T__13"

        protected DFA1 dfa1 = new DFA1(this);
        // $ANTLR end "T__14"
        boolean noncorrect = false;
        // $ANTLR end "T__15"

        @SuppressWarnings("empty-statement")
        public edapl_langLexer() {
        }
        // $ANTLR end "T__16"

        public edapl_langLexer(CharStream input) {
            this(input, new RecognizerSharedState());
        }
        // $ANTLR end "T__17"

        public edapl_langLexer(CharStream input, RecognizerSharedState state) {
            super(input, state);
        }
        // $ANTLR end "T__18"

        @Override
        public String getGrammarFileName() {
            return "edapl_lang__.g";
        }
        // $ANTLR end "T__19"

        // $ANTLR start "T__4"
        public final void mT__4() throws RecognitionException {
            try {
                int _type = T__4;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:3:6: ( 'BEGIN' )
                // edapl_lang__.g:3:8: 'BEGIN'
                {
                    match("BEGIN");
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }
        // $ANTLR end "T__20"

        // $ANTLR start "T__5"
        public final void mT__5() throws RecognitionException {
            try {
                int _type = T__5;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:4:6: ( 'END' )
                // edapl_lang__.g:4:8: 'END'
                {
                    match("END");
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }
        // $ANTLR end "T__21"

        // $ANTLR start "T__6"
        public final void mT__6() throws RecognitionException {
            try {
                int _type = T__6;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:5:6: ( 'LIN' )
                // edapl_lang__.g:5:8: 'LIN'
                {
                    match("LIN");
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }
        // $ANTLR end "T__22"

        // $ANTLR start "T__7"
        public final void mT__7() throws RecognitionException {
            try {
                int _type = T__7;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:6:6: ( 'y' )
                // edapl_lang__.g:6:8: 'y'
                {
                    match('y');
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }
        // $ANTLR end "T__23"

        // $ANTLR start "T__8"
        public final void mT__8() throws RecognitionException {
            try {
                int _type = T__8;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:7:6: ( 'ALT' )
                // edapl_lang__.g:7:8: 'ALT'
                {
                    match("ALT");
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }
        // $ANTLR end "T__24"

        // $ANTLR start "T__9"
        public final void mT__9() throws RecognitionException {
            try {
                int _type = T__9;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:8:6: ( 'x' )
                // edapl_lang__.g:8:8: 'x'
                {
                    match('x');
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }
        // $ANTLR end "T__25"

        // $ANTLR start "T__10"
        public final void mT__10() throws RecognitionException {
            try {
                int _type = T__10;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:9:7: ( 'ENDALT' )
                // edapl_lang__.g:9:9: 'ENDALT'
                {
                    match("ENDALT");
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }
        // $ANTLR end "T__26"

        // $ANTLR start "T__11"
        public final void mT__11() throws RecognitionException {
            try {
                int _type = T__11;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:10:7: ( 'PAR' )
                // edapl_lang__.g:10:9: 'PAR'
                {
                    match("PAR");
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__12"
        public final void mT__12() throws RecognitionException {
            try {
                int _type = T__12;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:11:7: ( 'P' )
                // edapl_lang__.g:11:9: 'P'
                {
                    match('P');
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__13"
        public final void mT__13() throws RecognitionException {
            try {
                int _type = T__13;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:12:7: ( 'P2' )
                // edapl_lang__.g:12:9: 'P2'
                {
                    match("P2");
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__14"
        public final void mT__14() throws RecognitionException {
            try {
                int _type = T__14;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:13:7: ( 'ENDPAR' )
                // edapl_lang__.g:13:9: 'ENDPAR'
                {
                    match("ENDPAR");
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__15"
        public final void mT__15() throws RecognitionException {
            try {
                int _type = T__15;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:14:7: ( 'LOOP' )
                // edapl_lang__.g:14:9: 'LOOP'
                {
                    match("LOOP");
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__16"
        public final void mT__16() throws RecognitionException {
            try {
                int _type = T__16;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:15:7: ( 'ENDLOOP' )
                // edapl_lang__.g:15:9: 'ENDLOOP'
                {
                    match("ENDLOOP");
                }

                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__17"
        public final void mT__17() throws RecognitionException {
            try {
                int _type = T__17;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:16:7: ( '0' )
                // edapl_lang__.g:16:9: '0'
                {
                    match('0');
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__18"
        public final void mT__18() throws RecognitionException {
            try {
                int _type = T__18;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:17:7: ( '1' )
                // edapl_lang__.g:17:9: '1'
                {
                    match('1');
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__19"
        public final void mT__19() throws RecognitionException {
            try {
                int _type = T__19;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:18:7: ( '2' )
                // edapl_lang__.g:18:9: '2'
                {
                    match('2');
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__20"
        public final void mT__20() throws RecognitionException {
            try {
                int _type = T__20;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:19:7: ( '3' )
                // edapl_lang__.g:19:9: '3'
                {
                    match('3');
                }

                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__21"
        public final void mT__21() throws RecognitionException {
            try {
                int _type = T__21;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:20:7: ( '4' )
                // edapl_lang__.g:20:9: '4'
                {
                    match('4');
                }
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__22"
        public final void mT__22() throws RecognitionException {
            try {
                int _type = T__22;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:21:7: ( '5' )
                // edapl_lang__.g:21:9: '5'
                {
                    match('5');
                }

                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__23"
        public final void mT__23() throws RecognitionException {
            try {
                int _type = T__23;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:22:7: ( '6' )
                // edapl_lang__.g:22:9: '6'
                match('6');
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__24"
        public final void mT__24() throws RecognitionException {
            try {
                int _type = T__24;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:23:7: ( '7' )
                // edapl_lang__.g:23:9: '7'
                match('7');
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__25"
        public final void mT__25() throws RecognitionException {
            try {
                int _type = T__25;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:24:7: ( '8' )
                // edapl_lang__.g:24:9: '8'
                match('8');
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        // $ANTLR start "T__26"
        public final void mT__26() throws RecognitionException {
            try {
                int _type = T__26;
                int _channel = DEFAULT_TOKEN_CHANNEL;
                // edapl_lang__.g:25:7: ( '9' )
                // edapl_lang__.g:25:9: '9'
                match('9');
                state.type = _type;
                state.channel = _channel;
            } catch (Exception e) {
                noncorrect = true;
            } finally {
            }
        }

        @Override
        public void mTokens() throws RecognitionException {
            // edapl_lang__.g:1:8: ( T__4 | T__5 | T__6 | T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 )
            int alt1 = 23;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1: // edapl_lang__.g:1:10: T__4
                {
                    mT__4();
                }
                break;
                case 2: // edapl_lang__.g:1:15: T__5
                {
                    mT__5();
                }
                break;
                case 3: // edapl_lang__.g:1:20: T__6
                {
                    mT__6();
                }
                break;
                case 4: // edapl_lang__.g:1:25: T__7
                {
                    mT__7();
                }
                break;
                case 5: // edapl_lang__.g:1:30: T__8
                {
                    mT__8();
                }
                break;
                case 6: // edapl_lang__.g:1:35: T__9
                {
                    mT__9();
                }
                break;
                case 7: // edapl_lang__.g:1:40: T__10
                {
                    mT__10();
                }
                break;
                case 8: // edapl_lang__.g:1:46: T__11
                {
                    mT__11();
                }
                break;
                case 9: // edapl_lang__.g:1:52: T__12
                {
                    mT__12();
                }
                break;
                case 10: // edapl_lang__.g:1:58: T__13
                {
                    mT__13();
                }
                break;
                case 11: // edapl_lang__.g:1:64: T__14
                {
                    mT__14();
                }
                break;
                case 12: // edapl_lang__.g:1:70: T__15
                {
                    mT__15();
                }
                break;
                case 13: // edapl_lang__.g:1:76: T__16
                {
                    mT__16();
                }
                break;
                case 14: // edapl_lang__.g:1:82: T__17
                {
                    mT__17();
                }
                break;
                case 15: // edapl_lang__.g:1:88: T__18
                {
                    mT__18();
                }
                break;
                case 16: // edapl_lang__.g:1:94: T__19
                {
                    mT__19();
                }
                break;
                case 17: // edapl_lang__.g:1:100: T__20
                {
                    mT__20();
                }
                break;
                case 18: // edapl_lang__.g:1:106: T__21
                {
                    mT__21();
                }
                break;
                case 19: // edapl_lang__.g:1:112: T__22
                {
                    mT__22();
                }
                break;
                case 20: // edapl_lang__.g:1:118: T__23
                {
                    mT__23();
                }
                break;
                case 21: // edapl_lang__.g:1:124: T__24
                {
                    mT__24();
                }
                break;
                case 22: // edapl_lang__.g:1:130: T__25
                {
                    mT__25();
                }
                break;
                case 23: // edapl_lang__.g:1:136: T__26
                {
                    mT__26();
                }
                break;
            }
        }

        public boolean check() {
            return noncorrect;
        }

        class DFA1 extends DFA {
            public DFA1(BaseRecognizer recognizer) {
                this.recognizer = recognizer;
                this.decisionNumber = 1;
                this.eot = DFA1_eot;
                this.eof = DFA1_eof;
                this.min = DFA1_min;
                this.max = DFA1_max;
                this.accept = DFA1_accept;
                this.special = DFA1_special;
                this.transition = DFA1_transition;
            }

            @Override
            public String getDescription() {
                return "1:1: Tokens : ( T__4 | T__5 | T__6 | T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 );";
            }
        }
    }

    // $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g 2013-06-24 17:35:07
    private static class edapl_langParser extends Parser {
        public static final String[] tokenNames = new String[]{
                "<invalid>", "<EOR>", "<DOWN>", "<UP>", "'BEGIN'", "'END'", "'LIN'", "'y'", "'ALT'", "'x'", "'ENDALT'", "'PAR'", "'P'", "'P2'", "'ENDPAR'", "'LOOP'", "'ENDLOOP'", "'0'", "'1'", "'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'"
        };
        public static final int EOF = -1;
        public static final int T__4 = 4;
        public static final int T__5 = 5;
        public static final int T__6 = 6;
        public static final int T__7 = 7;
        public static final int T__8 = 8;
        public static final int T__9 = 9;
        public static final int T__10 = 10;
        public static final int T__11 = 11;
        public static final int T__12 = 12;
        public static final int T__13 = 13;
        public static final int T__14 = 14;
        public static final int T__15 = 15;
        public static final int T__16 = 16;
        public static final int T__17 = 17;
        public static final int T__18 = 18;
        public static final int T__19 = 19;
        public static final int T__20 = 20;
        public static final int T__21 = 21;
        public static final int T__22 = 22;
        public static final int T__23 = 23;
        public static final int T__24 = 24;
        public static final int T__25 = 25;
        public static final int T__26 = 26;
        // $ANTLR end "num"
        // Delegated rules
        public static final BitSet FOLLOW_4_in_algorythm13 = new BitSet(new long[]{0x0000000000008940L});
        public static final BitSet FOLLOW_programbody_in_algorythm15 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_5_in_algorythm17 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_block_in_programbody28 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_lin_in_block41 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_condition_in_block46 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_6_in_lin62 = new BitSet(new long[]{0x0000000000000080L});
        // $ANTLR end "algorythm"
        public static final BitSet FOLLOW_7_in_lin65 = new BitSet(new long[]{0x0000000007FE0000L});
        // $ANTLR end "programbody"
        public static final BitSet FOLLOW_num_in_lin67 = new BitSet(new long[]{0x00000000000089C0L});
        // $ANTLR end "block"
        public static final BitSet FOLLOW_block_in_lin71 = new BitSet(new long[]{0x0000000000000002L});
        // $ANTLR end "lin"
        public static final BitSet FOLLOW_alt_in_condition89 = new BitSet(new long[]{0x0000000000000002L});
        // $ANTLR end "condition"
        public static final BitSet FOLLOW_par_in_condition94 = new BitSet(new long[]{0x0000000000000002L});
        // $ANTLR end "alt"
        public static final BitSet FOLLOW_loop_in_condition99 = new BitSet(new long[]{0x0000000000000002L});
        // $ANTLR end "par"
        public static final BitSet FOLLOW_8_in_alt111 = new BitSet(new long[]{0x0000000000000200L});
        // $ANTLR end "loop"
        public static final BitSet FOLLOW_9_in_alt113 = new BitSet(new long[]{0x0000000007FE0000L});
        public static final BitSet FOLLOW_num_in_alt115 = new BitSet(new long[]{0x0000000000008940L});
        public static final BitSet FOLLOW_block_in_alt117 = new BitSet(new long[]{0x0000000000000400L});
        public static final BitSet FOLLOW_10_in_alt119 = new BitSet(new long[]{0x0000000000008940L});
        public static final BitSet FOLLOW_block_in_alt121 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_par135 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_9_in_par137 = new BitSet(new long[]{0x0000000007FE0000L});
        public static final BitSet FOLLOW_num_in_par139 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_par141 = new BitSet(new long[]{0x0000000000008940L});
        public static final BitSet FOLLOW_block_in_par143 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_13_in_par145 = new BitSet(new long[]{0x0000000000008940L});
        public static final BitSet FOLLOW_block_in_par147 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_par149 = new BitSet(new long[]{0x0000000000008940L});
        public static final BitSet FOLLOW_block_in_par151 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_loop165 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_9_in_loop167 = new BitSet(new long[]{0x0000000007FE0000L});
        public static final BitSet FOLLOW_num_in_loop169 = new BitSet(new long[]{0x0000000000008940L});
        public static final BitSet FOLLOW_block_in_loop171 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_loop173 = new BitSet(new long[]{0x0000000000008940L});
        public static final BitSet FOLLOW_block_in_loop175 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_set_in_num189 = new BitSet(new long[]{0x0000000007FE0002L});
        boolean noncorrect = false;

        // delegates
        // delegators
        public edapl_langParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }

        public edapl_langParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
        }

        public final boolean check() throws RecognitionException {
            return noncorrect;
        }

        @Override
        public String[] getTokenNames() {
            return edapl_langParser.tokenNames;
        }

        @Override
        public String getGrammarFileName() {
            return "C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g";
        }

        // $ANTLR start "algorythm"
        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:4:1: algorythm : 'BEGIN' programbody 'END' ;
        public final void algorythm() throws RecognitionException {
            try {
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:5:2: ( 'BEGIN' programbody 'END' )
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:5:4: 'BEGIN' programbody 'END'
                match(input, 4, FOLLOW_4_in_algorythm13);
                pushFollow(FOLLOW_programbody_in_algorythm15);
                programbody();
                state._fsp--;
                match(input, 5, FOLLOW_5_in_algorythm17);
            } catch (RecognitionException re) {
                pr();
                reportError(re);
                recover(input, re);
            } finally {
            }
        }

        // $ANTLR start "programbody"
        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:8:1: programbody : block ;
        public final void programbody() throws RecognitionException {
            try {
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:9:2: ( block )
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:9:4: block
                pushFollow(FOLLOW_block_in_programbody28);
                block();
                state._fsp--;

            } catch (RecognitionException re) {
                pr();
                reportError(re);
                recover(input, re);
            } finally {
            }
        }

        // $ANTLR start "block"
        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:12:1: block : ( lin | condition );
        public final void block() throws RecognitionException {
            try {
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:13:2: ( lin | condition )
                int alt1 = 2;
                int LA1_0 = input.LA(1);

                if (((LA1_0 >= 5 && LA1_0 <= 6) || LA1_0 == 10 || (LA1_0 >= 13 && LA1_0 <= 14) || LA1_0 == 16)) {
                    alt1 = 1;
                } else if ((LA1_0 == 8 || LA1_0 == 11 || LA1_0 == 15)) {
                    alt1 = 2;
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 1, 0, input);
                    throw nvae;
                }
                switch (alt1) {
                    case 1: // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:13:4: lin
                    {
                        pushFollow(FOLLOW_lin_in_block41);
                        lin();
                        state._fsp--;
                    }
                    break;
                    case 2: // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:14:4: condition
                    {
                        pushFollow(FOLLOW_condition_in_block46);
                        condition();
                        state._fsp--;
                    }
                    break;
                }
            } catch (RecognitionException re) {
                pr();
                reportError(re);
                recover(input, re);
            } finally {
            }
        }

        // $ANTLR start "lin"
        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:19:1: lin : ( 'LIN' ( 'y' num )+ block | );
        public final void lin() throws RecognitionException {
            try {
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:20:2: ( 'LIN' ( 'y' num )+ block | )
                int alt3 = 2;
                int LA3_0 = input.LA(1);
                if ((LA3_0 == 6)) {
                    alt3 = 1;
                } else if ((LA3_0 == 5 || LA3_0 == 10 || (LA3_0 >= 13 && LA3_0 <= 14) || LA3_0 == 16)) {
                    alt3 = 2;
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 3, 0, input);
                    throw nvae;
                }
                switch (alt3) {
                    case 1: // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:20:5: 'LIN' ( 'y' num )+ block
                    {
                        match(input, 6, FOLLOW_6_in_lin62);
                        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:20:11: ( 'y' num )+
                        int cnt2 = 0;
                        loop2:
                        do {
                            int alt2 = 2;
                            int LA2_0 = input.LA(1);
                            if ((LA2_0 == 7)) {
                                alt2 = 1;
                            }
                            switch (alt2) {
                                case 1: // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:20:12: 'y' num
                                {
                                    match(input, 7, FOLLOW_7_in_lin65);
                                    pushFollow(FOLLOW_num_in_lin67);
                                    num();
                                    state._fsp--;
                                }
                                break;

                                default:
                                    if (cnt2 >= 1) {
                                        break loop2;
                                    }
                                    EarlyExitException eee =
                                            new EarlyExitException(2, input);
                                    throw eee;
                            }
                            cnt2++;
                        } while (true);
                        pushFollow(FOLLOW_block_in_lin71);
                        block();
                        state._fsp--;
                    }
                    break;
                    case 2: // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:22:2:
                    {
                    }
                    break;
                }
            } catch (RecognitionException re) {
                pr();
                reportError(re);
                recover(input, re);
            } finally {
            }
        }

        // $ANTLR start "condition"
        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:24:1: condition : ( alt | par | loop ) ;
        public final void condition() throws RecognitionException {
            try {
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:25:2: ( ( alt | par | loop ) )
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:25:4: ( alt | par | loop )
                {
                    // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:25:4: ( alt | par | loop )
                    int alt4 = 3;
                    switch (input.LA(1)) {
                        case 8: {
                            alt4 = 1;
                        }
                        break;
                        case 11: {
                            alt4 = 2;
                        }
                        break;
                        case 15: {
                            alt4 = 3;
                        }
                        break;
                        default:
                            NoViableAltException nvae =
                                    new NoViableAltException("", 4, 0, input);
                            throw nvae;
                    }
                    switch (alt4) {
                        case 1: // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:25:5: alt
                        {
                            pushFollow(FOLLOW_alt_in_condition89);
                            alt();
                            state._fsp--;
                        }
                        break;
                        case 2: // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:26:4: par
                        {
                            pushFollow(FOLLOW_par_in_condition94);
                            par();
                            state._fsp--;
                        }
                        break;
                        case 3: // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:27:4: loop
                        {
                            pushFollow(FOLLOW_loop_in_condition99);
                            loop();
                            state._fsp--;
                        }
                        break;
                    }
                }

            } catch (RecognitionException re) {
                pr();
                reportError(re);
                recover(input, re);
            } finally {
            }
        }

        // $ANTLR start "alt"
        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:30:1: alt : 'ALT' 'x' num block 'ENDALT' block ;
        public final void alt() throws RecognitionException {
            try {
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:31:2: ( 'ALT' 'x' num block 'ENDALT' block )
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:31:4: 'ALT' 'x' num block 'ENDALT' block
                {
                    match(input, 8, FOLLOW_8_in_alt111);
                    match(input, 9, FOLLOW_9_in_alt113);
                    pushFollow(FOLLOW_num_in_alt115);
                    num();
                    state._fsp--;
                    pushFollow(FOLLOW_block_in_alt117);
                    block();
                    state._fsp--;
                    match(input, 10, FOLLOW_10_in_alt119);
                    pushFollow(FOLLOW_block_in_alt121);
                    block();
                    state._fsp--;
                }
            } catch (RecognitionException re) {
                pr();
                reportError(re);
                recover(input, re);
            } finally {
            }
        }

        // $ANTLR start "par"
        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:34:1: par : 'PAR' 'x' num 'P' block 'P2' block 'ENDPAR' block ;
        public final void par() throws RecognitionException {
            try {
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:35:2: ( 'PAR' 'x' num 'P' block 'P2' block 'ENDPAR' block )
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:35:4: 'PAR' 'x' num 'P' block 'P2' block 'ENDPAR' block
                {
                    match(input, 11, FOLLOW_11_in_par135);
                    match(input, 9, FOLLOW_9_in_par137);
                    pushFollow(FOLLOW_num_in_par139);
                    num();
                    state._fsp--;
                    match(input, 12, FOLLOW_12_in_par141);
                    pushFollow(FOLLOW_block_in_par143);
                    block();
                    state._fsp--;
                    match(input, 13, FOLLOW_13_in_par145);
                    pushFollow(FOLLOW_block_in_par147);
                    block();
                    state._fsp--;
                    match(input, 14, FOLLOW_14_in_par149);
                    pushFollow(FOLLOW_block_in_par151);
                    block();
                    state._fsp--;
                }
            } catch (RecognitionException re) {
                pr();
                reportError(re);
                recover(input, re);
            } finally {
            }
        }

        // $ANTLR start "loop"
        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:38:1: loop : 'LOOP' 'x' num block 'ENDLOOP' block ;
        public final void loop() throws RecognitionException {
            try {
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:39:2: ( 'LOOP' 'x' num block 'ENDLOOP' block )
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:39:4: 'LOOP' 'x' num block 'ENDLOOP' block
                {
                    match(input, 15, FOLLOW_15_in_loop165);
                    match(input, 9, FOLLOW_9_in_loop167);
                    pushFollow(FOLLOW_num_in_loop169);
                    num();
                    state._fsp--;
                    pushFollow(FOLLOW_block_in_loop171);
                    block();
                    state._fsp--;
                    match(input, 16, FOLLOW_16_in_loop173);
                    pushFollow(FOLLOW_block_in_loop175);
                    block();
                    state._fsp--;
                }

            } catch (RecognitionException re) {
                pr();
                reportError(re);
                recover(input, re);
            } finally {
            }
        }

        // $ANTLR start "num"
        // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:42:1: num : ( '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' )+ ;
        public final void num() throws RecognitionException {
            try {
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:43:2: ( ( '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' )+ )
                // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:43:4: ( '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' )+
                {
                    // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:43:4: ( '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' )+
                    int cnt5 = 0;
                    loop5:
                    do {
                        int alt5 = 2;
                        int LA5_0 = input.LA(1);

                        if (((LA5_0 >= 17 && LA5_0 <= 26))) {
                            alt5 = 1;
                        }
                        switch (alt5) {
                            case 1: // C:\\Documents and Settings\\Владислав\\Desktop\\edapl_lang.g:
                            {
                                if ((input.LA(1) >= 17 && input.LA(1) <= 26)) {
                                    input.consume();
                                    state.errorRecovery = false;
                                } else {
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    throw mse;
                                }
                            }
                            break;

                            default:
                                if (cnt5 >= 1) {
                                    break loop5;
                                }
                                EarlyExitException eee =
                                        new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);
                }

            } catch (RecognitionException re) {
                pr();
                reportError(re);
                recover(input, re);
            } finally {
            }
        }

        private void pr() {
            //System.out.println("error===============================================>");
            noncorrect = true;
        }
    }
}
