/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueRankingRewardGroupDetailComponent } from 'app/entities/m-league-ranking-reward-group/m-league-ranking-reward-group-detail.component';
import { MLeagueRankingRewardGroup } from 'app/shared/model/m-league-ranking-reward-group.model';

describe('Component Tests', () => {
  describe('MLeagueRankingRewardGroup Management Detail Component', () => {
    let comp: MLeagueRankingRewardGroupDetailComponent;
    let fixture: ComponentFixture<MLeagueRankingRewardGroupDetailComponent>;
    const route = ({ data: of({ mLeagueRankingRewardGroup: new MLeagueRankingRewardGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueRankingRewardGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLeagueRankingRewardGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLeagueRankingRewardGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLeagueRankingRewardGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
