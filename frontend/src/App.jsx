import { useState, useEffect, useRef, useCallback } from "react";

const COLORS = {
  active:     { bg: "#dbeafe", border: "#3b82f6", text: "#1e40af" },
  secondary:  { bg: "#fef3c7", border: "#f59e0b", text: "#92400e" },
  done:       { bg: "#dcfce7", border: "#22c55e", text: "#4c1d95" },
  eliminated: { bg: "#f1f5f9", border: "#cbd5e1", text: "#94a3b8" },
  swap:       { bg: "#ede9fe", border: "#8b5cf6", text: "#4c1d95" },
  idle:       { bg: "#ffffff", border: "#e2e8f0", text: "#334155" },
};

function cellState(idx, step) {
  if (!step) return "idle";
  if (step.swap?.includes(idx)) return "swap";
  if (step.highlight?.includes(idx)) return "active";
  if (step.secondary?.includes(idx)) return "secondary";
  if (step.eliminated?.includes(idx)) return "eliminated";
  if (step.done?.includes(idx)) return "done";
  return "idle";
}

function ArrayViz({ step }) {
  if (!step?.arr) return null;
  return (
    <div style={{ display:"flex", flexWrap:"wrap", gap:8, alignItems:"flex-end", minHeight:100, padding:"8px 0" }}>
      {step.arr.map((val, idx) => {
        const s = COLORS[cellState(idx, step)];
        const ptr = step.pointers?.[idx];
        return (
          <div key={idx} style={{ display:"flex", flexDirection:"column", alignItems:"center", gap:4 }}>
            <span style={{ fontSize:10, fontWeight:700, color:ptr?"#3b82f6":"transparent", fontFamily:"monospace", minHeight:14 }}>{ptr || "."}</span>
            <div style={{ width:50, height:50, borderRadius:10, border:`3px solid ${s.border}`, background:s.bg, color:s.text, display:"flex", alignItems:"center", justifyContent:"center", fontSize:17, fontWeight:700, fontFamily:"monospace", transition:"all 0.3s" }}>{val}</div>
            <span style={{ fontSize:10, color:"#94a3b8", fontFamily:"monospace" }}>[{idx}]</span>
          </div>
        );
      })}
    </div>
  );
}

function CodePanel({ lines, activeLine }) {
  const ref = useRef(null);
  useEffect(() => { ref.current?.scrollIntoView({ behavior:"smooth", block:"nearest" }); }, [activeLine]);
  return (
    <div style={{ background:"#0d1117", borderRadius:10, overflow:"hidden", fontSize:13, fontFamily:"monospace", border:"1px solid #30363d" }}>
      <div style={{ padding:"10px 14px", background:"#161b22", borderBottom:"1px solid #30363d", display:"flex", gap:6 }}>
        { ["#ff5f57","#febc2e","#28c840"].map(c => <div key={c} style={{ width:10, height:10, borderRadius:"50%", background:c }} />) }
        <span style={{ marginLeft:10, fontSize:11, color:"#8b949e" }}>algorithm</span>
      </div>
      <div style={{ padding:"8px 0", maxHeight:280, overflowY:"auto" }}>
        {(lines||[]).map((item, i) => (
          <div key={i} ref={i===activeLine?ref:null} style={{ padding:"5px 14px", background:i===activeLine?"rgba(59,130,246,0.18)":"transparent", borderLeft:`3px solid ${i===activeLine?"#3b82f6":"transparent"}`, display:"flex", gap:12, alignItems:"center", transition:"all 0.2s" }}>
            <span style={{ color:"#4b5563", fontSize:11, minWidth:20, textAlign:"right", fontWeight:600 }}>{i+1}</span>
            <span style={{ color:i===activeLine?"#e2e8f0":"#8b949e", flex:1, whiteSpace:"pre" }}>{item.line}</span>
            {i===activeLine && item.explain && <span style={{ fontSize:11, color:"#34d399", borderLeft:"1px solid #1f4a3a", paddingLeft:10, whiteSpace:"nowrap", maxWidth:180, overflow:"hidden", textOverflow:"ellipsis" }}>{"<- "}{item.explain}</span>}
          </div>
        ))}
      </div>
    </div>
  );
}

function Dots({ label }) {
  const [d, setD] = useState(".");
  useEffect(() => { const t = setInterval(() => setD(p => p.length >= 3 ? "." : p + "."), 400); return () => clearInterval(t); }, []);
  return <span style={{ color:"#94a3b8", fontSize:14, fontWeight:500 }}>{label}{d}</span>;
}

const B = { padding:"10px 18px", borderRadius:8, border:"2px solid #e2e8f0", background:"white", color:"#334155", cursor:"pointer", fontSize:14, fontWeight:600, fontFamily:"inherit", transition:"all 0.15s" };
const API_BASE = import.meta.env.VITE_API_URL || "";

function parseInput(value) {
  if (!value) return [];
  const cleaned = value.replace(/\[/g, " ").replace(/\]/g, " ").replace(/,/g, " ").trim();
  return cleaned.split(/\s+/).filter(Boolean).map(v => Number(v)).filter(n => !Number.isNaN(n));
}

function parseTarget(value) {
  if (!value) return null;
  const parsed = Number(value.trim());
  return Number.isNaN(parsed) ? null : parsed;
}

function findDelimiterError(code) {
  const pairs = { "(": ")", "[": "]", "{": "}" };
  const openers = Object.keys(pairs);
  const closers = Object.values(pairs);
  const stack = [];
  let line = 1;
  let inSingle = false;
  let inDouble = false;
  let inBack = false;
  let inLineComment = false;
  let inBlockComment = false;

  for (let i = 0; i < code.length; i++) {
    const char = code[i];
    const next = code[i + 1];
    const prev = code[i - 1];

    if (char === "\n") {
      line += 1;
      inLineComment = false;
      continue;
    }

    if (inLineComment) {
      continue;
    }

    if (inBlockComment) {
      if (char === "/" && prev === "*") {
        inBlockComment = false;
      }
      continue;
    }

    if (inSingle) {
      if (char === "'" && prev !== "\\") {
        inSingle = false;
      }
      continue;
    }

    if (inDouble) {
      if (char === '"' && prev !== "\\") {
        inDouble = false;
      }
      continue;
    }

    if (inBack) {
      if (char === "`" && prev !== "\\") {
        inBack = false;
      }
      continue;
    }

    if (char === "/" && next === "/") {
      inLineComment = true;
      i += 1;
      continue;
    }

    if (char === "/" && next === "*") {
      inBlockComment = true;
      i += 1;
      continue;
    }

    if (char === "'") {
      inSingle = true;
      continue;
    }

    if (char === '"') {
      inDouble = true;
      continue;
    }

    if (char === "`") {
      inBack = true;
      continue;
    }

    if (openers.includes(char)) {
      stack.push({ char, line });
      continue;
    }

    if (closers.includes(char)) {
      const last = stack[stack.length - 1];
      if (!last || pairs[last.char] !== char) {
        return { message: `Unexpected '${char}'.`, line };
      }
      stack.pop();
    }
  }

  if (inSingle || inDouble || inBack) {
    return { message: "Unterminated string literal.", line };
  }

  if (inBlockComment) {
    return { message: "Unterminated block comment.", line };
  }

  if (stack.length) {
    const last = stack[stack.length - 1];
    return { message: `Unmatched '${last.char}'.`, line: last.line };
  }

  return null;
}

function validateCodeSyntax(code, language) {
  if (!code || !code.trim()) return null;
  const delimiterError = findDelimiterError(code);
  if (delimiterError) {
    return delimiterError;
  }

  if (language === "JavaScript" || language === "TypeScript") {
    try {
      new Function(code);
      return null;
    } catch (error) {
      const message = error.message.replace(/\n.*$/, "");
      const match = message.match(/\((\d+):\d+\)/);
      return { message, line: match ? Number(match[1]) : 1 };
    }
  }

  if (language === "Python") {
    const lines = code.split("\n");
    const blockHeader = /^(if|elif|else|for|while|def|class|try|except|finally|with)\b/;
    for (let i = 0; i < lines.length; i++) {
      const trimmed = lines[i].trim();
      if (!trimmed || trimmed.startsWith("#")) continue;
      if (blockHeader.test(trimmed) && !trimmed.endsWith(":")) {
        return { message: "Missing ':' after block header.", line: i + 1 };
      }
    }
  }

  return null;
}

export default function DSAAnalyzer() {
  const [algorithms, setAlgorithms] = useState([]);
  const [selectedId, setSelectedId] = useState("");
  const [selectedLanguage, setSelectedLanguage] = useState("All");
  const [selectedAlgorithm, setSelectedAlgorithm] = useState(null);
  const [codeById, setCodeById] = useState({});
  const [code, setCode] = useState("");
  const [inputText, setInputText] = useState("");
  const [targetText, setTargetText] = useState("");
  const [phase, setPhase] = useState("idle");
  const [result, setResult] = useState(null);
  const [error, setError] = useState("");
  const [codeError, setCodeError] = useState(null);
  const activeValidationLanguageRef = useRef(null);

  const [stepIdx, setStepIdx] = useState(0);
  const [playing, setPlaying] = useState(false);
  const [speed, setSpeed] = useState(3);
  const timerRef = useRef(null);
  const codeTextareaRef = useRef(null);
  const lineNumbersRef = useRef(null);
  const SPEEDS = [1600, 900, 500, 220, 80];
  const SLABELS = ["Slowest", "Slow", "Normal", "Fast", "Fastest"];

  const languages = ["All", ...Array.from(new Set(algorithms.map(item => item.language))).sort()];
  const filteredAlgorithms = algorithms;
  const lineCount = Math.max(1, code.split("\n").length);

  useEffect(() => {
    // Validate against the language whose code is currently visible.
    // This prevents stale language/code pairing causing false syntax errors.
    const langToValidate = activeValidationLanguageRef.current || selectedLanguage;
    setCodeError(validateCodeSyntax(code, langToValidate || ""));
  }, [code, selectedLanguage]);


  useEffect(() => {
    if (!codeError && error?.startsWith("Fix code errors")) {
      setError("");
      if (phase === "error") {
        setPhase("idle");
      }
    }
  }, [codeError, error, phase]);

  useEffect(() => {
    fetch(`${API_BASE}/api/algorithms`)
      .then(res => res.ok ? res.json() : Promise.reject(new Error(`Failed to load algorithms (${res.status})`)))
      .then(data => {
        setAlgorithms(data || []);
        if (data && data.length) {
          setSelectedId(data[0].id);
        }
      })
      .catch(err => {
        setError(err.message || "Unable to load algorithms.");
      });
  }, []);

  useEffect(() => {
    if (!algorithms.length) return;
    if (!selectedId || !algorithms.some(item => item.id === selectedId)) {
      setSelectedId(algorithms[0].id);
    }
  }, [algorithms, selectedId]);

  useEffect(() => {
    if (!selectedId || !algorithms.length) return;
    const algorithm = algorithms.find(item => item.id === selectedId);
    if (!algorithm) return;
    setSelectedAlgorithm(algorithm);
    const compositeKey = `${selectedId}__${selectedLanguage}`;
    const drafted = codeById[compositeKey];
    const langCode = algorithm.codes && selectedLanguage ? algorithm.codes[selectedLanguage] : undefined;
    const initialCode = drafted !== undefined ? drafted : (langCode !== undefined ? langCode : algorithm.code || "");
    setCode(initialCode);
    // Capture the actual language whose code is being shown.
    // This fixes global syntax errors when switching algorithms/languages.
    activeValidationLanguageRef.current = (selectedLanguage === "All" ? algorithm.language : selectedLanguage);

    if (drafted === undefined) {
      setCodeById(prev => ({ ...prev, [compositeKey]: initialCode }));
    }
    setInputText((algorithm.defaultInput || []).join(", "));
    setTargetText("");
    setResult(null);
    setPhase("idle");
    setError("");
    setStepIdx(0);
    setPlaying(false);
    clearTimeout(timerRef.current);
  }, [selectedId, algorithms, selectedLanguage]);

  const steps = result?.steps || [];
  const cur = steps[stepIdx] || null;

  const tick = useCallback(() => {
    setStepIdx(current => {
      if (current >= steps.length - 1) {
        setPlaying(false);
        return current;
      }
      return current + 1;
    });
  }, [steps.length]);

  useEffect(() => {
    if (playing) {
      timerRef.current = setTimeout(tick, SPEEDS[speed - 1]);
    }
    return () => clearTimeout(timerRef.current);
  }, [playing, stepIdx, speed, tick]);

  async function runAlgorithm() {
    if (!selectedAlgorithm) return;
    if (codeError) {
      setError(`Fix code errors before running: ${codeError.message}`);
      setPhase("error");
      return;
    }
    const parsedInput = parseInput(inputText);
    if (!parsedInput.length) {
      setError("Enter a valid array of numbers to run the algorithm.");
      return;
    }
    if (selectedAlgorithm.requiresTarget && parseTarget(targetText) === null) {
      setError("This algorithm requires a numeric target value.");
      return;
    }

    setPhase("loading");
    setError("");
    setResult(null);
    setPlaying(false);
    clearTimeout(timerRef.current);
    setStepIdx(0);

    try {
      const response = await fetch(`${API_BASE}/api/algorithms/${selectedId}/run`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ input: parsedInput, target: parseTarget(targetText) })
      });
      if (!response.ok) {
        throw new Error(`Backend error ${response.status}`);
      }
      const data = await response.json();
      if (!data || data.isValid === false) {
        throw new Error(data?.explanation || "Algorithm execution failed.");
      }
      setResult(data);
      setPhase("ready");
    } catch (err) {
      setError(err.message || "Failed to run algorithm.");
      setPhase("error");
    }
  }

  function handlePlay() {
    if (stepIdx >= steps.length - 1) {
      setStepIdx(0);
      setPlaying(true);
      return;
    }
    setPlaying(p => !p);
  }

  const pb = {
    idle: { label: "Ready to run", bg: "#f1f5f9", color: "#475569" },
    loading: { label: "Running algorithm...", bg: "#dbeafe", color: "#1e40af" },
    ready: { label: "Visualization ready", bg: "#dcfce7", color: "#166534" },
    error: { label: "Problem", bg: "#fee2e2", color: "#991b1b" }
  }[phase] || { label: "Ready", bg: "#f1f5f9", color: "#475569" };

  const card = { background: "white", border: "2px solid #e2e8f0", borderRadius: 16, overflow: "hidden", marginBottom: 20 };
  const ch = { padding: "12px 20px", borderBottom: "1px solid #f1f5f9", background: "#fafafa", display: "flex", alignItems: "center", gap: 12, flexWrap: "wrap" };
  const lbl = { fontSize: 12, fontWeight: 700, color: "#64748b", letterSpacing: 1, textTransform: "uppercase" };

  return (
    <div style={{ fontFamily: "'IBM Plex Mono','Courier New',monospace", background: "#f8fafc", minHeight: "100vh", width: "100%", padding: "32px 20px", color: "#0f172a" }}>
      <div style={{ maxWidth: 1300, margin: "0 auto", width: "100%" }}>
        <div style={{ marginBottom: 32, display: "flex", alignItems: "flex-end", justifyContent: "space-between", flexWrap: "wrap", gap: 16 }}>
          <div>
            <div style={{ fontSize: 12, fontWeight: 700, color: "#3b82f6", letterSpacing: 2, textTransform: "uppercase", marginBottom: 8 }}>DSA Lab</div>
            <h1 style={{ fontSize: 32, fontWeight: 800, margin: 0, letterSpacing: -0.5, lineHeight: 1.1 }}>Algorithm Analyzer<br/><span style={{ color: "#3b82f6" }}>& Visualizer</span></h1>
          </div>
          <span style={{ fontSize: 12, fontWeight: 600, padding: "6px 16px", borderRadius: 20, background: pb.bg, color: pb.color }}>{pb.label}</span>
        </div>

        <div style={card}>
          <div style={ch}>
            <span style={lbl}>Choose an algorithm</span>
            <span style={{ fontSize: 12, color: "#94a3b8" }}>Backend-powered demos with step-by-step traces</span>
          </div>
          <div style={{ padding: "18px 20px", display: "grid", gridTemplateColumns: "repeat(auto-fit,minmax(220px,1fr))", gap: 16 }}>
            <label style={{ display: "flex", flexDirection: "column", gap: 10 }}>
              <span style={{ fontSize: 12, fontWeight: 700, color: "#64748b", letterSpacing: 1, textTransform: "uppercase" }}>Select language</span>
              <select value={selectedLanguage} onChange={e => setSelectedLanguage(e.target.value)} style={{ width: "100%", border: "2px solid #e2e8f0", borderRadius: 12, padding: "12px 14px", fontSize: 14, color: "#0f172a", background: "white" }}>
                {languages.map((language) => (
                  <option key={language} value={language}>{language}</option>
                ))}
              </select>
            </label>

            <label style={{ display: "flex", flexDirection: "column", gap: 10 }}>
              <span style={{ fontSize: 12, fontWeight: 700, color: "#64748b", letterSpacing: 1, textTransform: "uppercase" }}>Select algorithm</span>
              <select value={selectedId} onChange={e => setSelectedId(e.target.value)} style={{ width: "100%", border: "2px solid #e2e8f0", borderRadius: 12, padding: "12px 14px", fontSize: 14, color: "#0f172a", background: "white" }}>
                {filteredAlgorithms.length ? filteredAlgorithms.map((algo) => (
                  <option key={algo.id} value={algo.id}>{algo.label}</option>
                )) : <option value="">No algorithms available</option>}
              </select>
            </label>
          </div>
          <div style={{ padding: "0 20px 20px" }}>
            {selectedAlgorithm ? (
              <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 16, marginTop: 16 }}>
                <div>
                  <div style={{ fontSize: 15, fontWeight: 700, marginBottom: 8 }}>{selectedAlgorithm.description}</div>
                  <div style={{ display: "flex", flexWrap: "wrap", gap: 10 }}>
                    <span style={{ background: "#eef2ff", color: "#4338ca", borderRadius: 8, padding: "6px 12px", fontSize: 12 }}>{selectedLanguage}</span>
                    <span style={{ background: "#f0fdf4", color: "#166534", borderRadius: 8, padding: "6px 12px", fontSize: 12 }}>{selectedAlgorithm.category}</span>
                  </div>
                </div>
                <div style={{ display: "flex", flexDirection: "column", gap: 8 }}>
                  <div style={{ fontSize: 12, color: "#94a3b8", letterSpacing: 1, textTransform: "uppercase", fontWeight: 600 }}>Example input</div>
                  <div style={{ fontSize: 15, color: "#334155", fontFamily: "monospace", background: "#f8fafc", padding: "12px 14px", borderRadius: 10, border: "1px solid #e2e8f0" }}>{(selectedAlgorithm.defaultInput || []).join(", ")}</div>
                </div>
              </div>
            ) : (
              <div style={{ fontSize: 14, color: "#64748b", padding: "14px 0" }}>Loading algorithms...</div>
            )}
          </div>
        </div>

        <div style={card}>
          <div style={ch}>
            <span style={lbl}>Input configuration</span>
            <span style={{ fontSize: 12, color: "#94a3b8" }}>Enter a numeric array and target when required.</span>
          </div>
          <div style={{ display: "grid", gap: 16, padding: "20px" }}>
            <div>
              <label style={{ display: "block", fontSize: 13, fontWeight: 700, color: "#475569", marginBottom: 8 }}>Array values</label>
              <input value={inputText} onChange={e => setInputText(e.target.value)} placeholder="e.g. 5, 3, 8, 4" style={{ width: "100%", border: "2px solid #e2e8f0", borderRadius: 12, padding: "12px 14px", fontSize: 14, fontFamily: "monospace", color: "#0f172a" }} />
            </div>
            {selectedAlgorithm?.requiresTarget && (
              <div>
                <label style={{ display: "block", fontSize: 13, fontWeight: 700, color: "#475569", marginBottom: 8 }}>Target value</label>
                <input value={targetText} onChange={e => setTargetText(e.target.value)} placeholder="e.g. 9" style={{ width: "100%", border: "2px solid #e2e8f0", borderRadius: 12, padding: "12px 14px", fontSize: 14, fontFamily: "monospace", color: "#0f172a" }} />
              </div>
            )}
            <div>
              <label style={{ display: "block", fontSize: 13, fontWeight: 700, color: "#475569", marginBottom: 8 }}>Algorithm code</label>
              <div style={{ display: "grid", gridTemplateColumns: "48px 1fr", minHeight: 220, border: `2px solid ${codeError ? "#f87171" : "#e2e8f0"}`, borderRadius: 12, overflow: "hidden", background: "#0f172a" }}>
                <div ref={lineNumbersRef} style={{ padding: "16px 8px", background: "#0b1120", color: "#64748b", fontFamily: "'IBM Plex Mono',monospace", fontSize: 14, lineHeight: 1.6, textAlign: "right", userSelect: "none", overflow: "hidden" }}>
                  {Array.from({ length: lineCount }, (_, i) => (
                    <div key={i} style={{ padding: "0 8px", minHeight: 22, background: codeError?.line === i + 1 ? "rgba(248, 113, 113, 0.16)" : "transparent", color: codeError?.line === i + 1 ? "#b91c1c" : "#64748b" }}>{i + 1}</div>
                  ))}
                </div>
                <textarea
                  ref={codeTextareaRef}
                  value={code}
                  onScroll={e => {
                    if (lineNumbersRef.current) {
                      lineNumbersRef.current.scrollTop = e.target.scrollTop;
                    }
                  }}
                  onChange={e => {
                    setCode(e.target.value);
                    if (selectedId) {
                      const compositeKey = `${selectedId}__${selectedLanguage}`;
                      setCodeById(prev => ({ ...prev, [compositeKey]: e.target.value }));
                    }
                  }}
                  placeholder="Write or edit algorithm code here..."
                  spellCheck={false}
                  style={{ width: "100%", minHeight: 220, border: "none", outline: "none", padding: "16px 18px", fontFamily: "'IBM Plex Mono',monospace", fontSize: 14, lineHeight: 1.6, resize: "vertical", color: "#e2e8f0", background: "#0d1117", overflow: "auto" }}
                />
              </div>
              {codeError && (
                <div style={{ marginTop: 12, padding: "14px 16px", borderRadius: 12, background: "#fef2f2", border: "1px solid #fecaca", color: "#991b1b", fontSize: 13 }}>
                  <strong>Code issue:</strong> line {codeError.line}: {codeError.message}
                </div>
              )}
            </div>
            <div style={{ display: "flex", gap: 12, flexWrap: "wrap", alignItems: "center" }}>
              <button onClick={runAlgorithm} disabled={!selectedAlgorithm || phase === "loading" || Boolean(codeError)} style={{ ...B, border: "none", background: phase === "loading" || codeError ? "#f1f5f9" : "#0f1720", color: phase === "loading" || codeError ? "#94a3b8" : "white", padding: "14px 28px", fontSize: 15, fontWeight: 700 }}>
                {phase === "loading" ? <Dots label="Running" /> : "Run Algorithm"}
              </button>
              {error && <span style={{ fontSize: 13, color: "#dc2626" }}>{error}</span>}
            </div>
          </div>
        </div>

        {result && (
          <>
            <div style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit,minmax(200px,1fr))", gap: 14, marginBottom: 20 }}>
              {[ ["Algorithm", result.algorithmName, "#0f172a"], ["Category", result.category, "#3b82f6"], ["Time", result.timeComplexity, "#8b5cf6"], ["Space", result.spaceComplexity, "#06b6d4"] ].map(([label,value,color]) => (
                <div key={label} style={{ background: "white", border: "2px solid #e2e8f0", borderRadius: 12, padding: "16px 18px" }}>
                  <div style={{ fontSize: 11, color: "#94a3b8", fontWeight: 700, letterSpacing: 1, textTransform: "uppercase", marginBottom: 8 }}>{label}</div>
                  <div style={{ fontSize: 18, fontWeight: 800, color }}>{value}</div>
                </div>
              ))}
            </div>
            <div style={{ background: "#f0fdf4", border: "2px solid #86efac", borderRadius: 12, padding: "18px 20px", marginBottom: 20 }}>
              <div style={{ fontSize: 15, fontWeight: 700, color: "#166534", marginBottom: 8 }}>Result</div>
              <div style={{ fontSize: 13, color: "#14532d", lineHeight: 1.7 }}>{result.explanation}</div>
            </div>
            <div style={card}>
              <div style={{ padding: "12px 20px", background: "#0d1117", display: "flex", alignItems: "center", justifyContent: "space-between" }}>
                <span style={{ fontSize: 12, fontWeight: 700, color: "#58a6ff", letterSpacing: 1, textTransform: "uppercase" }}>Live Visualization</span>
                <span style={{ fontSize: 12, color: "#6e7681" }}>Step {stepIdx + 1} of {steps.length}</span>
              </div>
              <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 0 }}>
                <div style={{ padding: 20, borderRight: "1px solid #e2e8f0" }}>
                  <div style={{ fontSize: 11, fontWeight: 700, color: "#94a3b8", letterSpacing: 1, textTransform: "uppercase", marginBottom: 10 }}>Array state</div>
                  <ArrayViz step={cur} />
                  <div style={{ display: "flex", flexWrap: "wrap", gap: 10, marginTop: 12, paddingTop: 12, borderTop: "1px solid #f1f5f9" }}>
                    {[ ["active", "#dbeafe", "#3b82f6"], ["comparing", "#fef3f7", "#f59e0b"], ["done", "#dcfce7", "#22c55e"], ["swapping", "#ede9fe", "#8b5cf6"], ["skipped", "#f1f5f9", "#cbd5e1"] ].map(([label,bg,border]) => (
                      <div key={label} style={{ display: "flex", alignItems: "center", gap: 5 }}>
                        <div style={{ width: 13, height: 13, borderRadius: 3, background: bg, border: `2px solid ${border}` }} />
                        <span style={{ fontSize: 11, color: "#94a3b8" }}>{label}</span>
                      </div>
                    ))}
                  </div>
                  {cur?.pointers && Object.keys(cur.pointers).length > 0 && (
                    <div style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit,minmax(140px,1fr))", gap: 10, marginTop: 14 }}>
                      {Object.entries(cur.pointers).map(([idx, label]) => (
                        <div key={`${idx}-${label}`} style={{ background: "#f8fafc", borderRadius: 8, padding: "10px 12px", border: "1px solid #e2e8f0" }}>
                          <div style={{ fontSize: 10, color: "#94a3b8", textTransform: "uppercase", fontWeight: 600 }}>{label}</div>
                          <div style={{ fontSize: 16, fontWeight: 800, fontFamily: "monospace", color: "#3b82f6" }}>idx={idx}</div>
                        </div>
                      ))}
                    </div>
                  )}
                  <div style={{ marginTop: 14, background: "#f0f9ff", border: "2px solid #bae6fd", borderRadius: 10, padding: "14px 16px" }}>
                    <div style={{ fontSize: 11, fontWeight: 700, color: "#0369a1", marginBottom: 6, textTransform: "uppercase", letterSpacing: 0.5 }}>What is happening</div>
                    <p style={{ margin: 0, fontSize: 13, color: "#0c4a6e", lineHeight: 1.7 }}>{cur?.msg || "Use run to begin algorithm visualization."}</p>
                  </div>
                </div>
                <div style={{ padding: 20 }}>
                  <div style={{ fontSize: 11, fontWeight: 700, color: "#94a3b8", letterSpacing: 1, textTransform: "uppercase", marginBottom: 10 }}>Code (active line)</div>
                  <CodePanel lines={result.codeLines} activeLine={cur?.activeLine ?? -1} />
                </div>
              </div>
              <div style={{ padding: "14px 20px", borderTop: "1px solid #e2e8f0", display: "flex", gap: 10, alignItems: "center", flexWrap: "wrap", background: "#fafafa" }}>
                <button onClick={handlePlay} style={{ ...B, background: playing ? "#fef2f2" : "#f0fdf4", color: playing ? "#dc2626" : "#16a34a", border: `2px solid ${playing ? "#fca5a5" : "#86efac"}`, minWidth: 90, fontWeight: 600 }}>{playing ? "Pause" : "Play"}</button>
                <button onClick={() => { setPlaying(false); clearTimeout(timerRef.current); if (stepIdx < steps.length - 1) setStepIdx(s => s + 1); }} disabled={!steps.length || stepIdx >= steps.length - 1} style={{ ...B, fontWeight: 600 }}>Step +</button>
                <button onClick={() => { setPlaying(false); clearTimeout(timerRef.current); if (stepIdx > 0) setStepIdx(s => s - 1); }} disabled={stepIdx === 0} style={{ ...B, fontWeight: 600 }}>Step -</button>
                <button onClick={() => { setPlaying(false); clearTimeout(timerRef.current); setStepIdx(0); }} style={{ ...B, fontWeight: 600 }}>Reset</button>
                <div style={{ flex: 1, minWidth: 80, height: 6, background: "#e2e8f0", borderRadius: 3, overflow: "hidden" }}>
                  <div style={{ height: "100%", background: "#3b82f6", width: `${steps.length ? ((stepIdx + 1) / steps.length) * 100 : 0}%`, transition: "width 0.3s", borderRadius: 3 }} />
                </div>
                <div style={{ display: "flex", alignItems: "center", gap: 8 }}>
                  <span style={{ fontSize: 12, color: "#94a3b8", fontWeight: 600 }}>Speed</span>
                  <input type="range" min="1" max="5" step="1" value={speed} onChange={e => setSpeed(+e.target.value)} style={{ width: 80 }} />
                  <span style={{ fontSize: 12, color: "#64748b", minWidth: 60, fontWeight: 600 }}>{SLABELS[speed - 1]}</span>
                </div>
              </div>
            </div>

            {result.howItWorks?.length > 0 && (
              <div style={card}>
                <div style={ch}><span style={lbl}>How it works</span></div>
                <div style={{ padding: "18px 20px", display: "flex", flexDirection: "column", gap: 12 }}>
                  {result.howItWorks.map((item, i) => (
                    <div key={i} style={{ display: "flex", gap: 12, alignItems: "flex-start" }}>
                      <div style={{ width: 28, height: 28, borderRadius: "50%", background: "#dbeafe", color: "#1e40af", fontSize: 13, fontWeight: 800, display: "flex", alignItems: "center", justifyContent: "center", flexShrink: 0, border: "2px solid #3b82f6" }}>{i + 1}</div>
                      <span style={{ fontSize: 13, color: "#374151", lineHeight: 1.7, paddingTop: 2 }}>{item}</span>
                    </div>
                  ))}
                </div>
              </div>
            )}
          </>
        )}

        {(!result && !error) && (
          <div style={{ textAlign: "center", padding: "60px 20px", color: "#94a3b8" }}>
            <div style={{ fontSize: 48, marginBottom: 16, fontFamily: "monospace", fontWeight: 300 }}>{"</>"}</div>
            <div style={{ fontSize: 18, fontWeight: 600, color: "#64748b", marginBottom: 8 }}>Select a demo and run it to see the algorithm step-by-step.</div>
            <div style={{ fontSize: 13, color: "#94a3b8", lineHeight: 1.6 }}>This app uses your own Java backend to compute visualization steps without external AI services.</div>
          </div>
        )}
      </div>
    </div>
  );
}
