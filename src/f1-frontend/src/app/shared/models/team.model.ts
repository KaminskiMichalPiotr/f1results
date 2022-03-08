export interface Team {

  teamName: string;
  teamTag: string;
  country: string;
  id: number | null;

}


export function emptyTeam(): Team {
  return {teamName: '', teamTag: '', country: '', id: null}
}
