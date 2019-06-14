/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRegulatedLeagueRankingRewardDetailComponent } from 'app/entities/m-regulated-league-ranking-reward/m-regulated-league-ranking-reward-detail.component';
import { MRegulatedLeagueRankingReward } from 'app/shared/model/m-regulated-league-ranking-reward.model';

describe('Component Tests', () => {
  describe('MRegulatedLeagueRankingReward Management Detail Component', () => {
    let comp: MRegulatedLeagueRankingRewardDetailComponent;
    let fixture: ComponentFixture<MRegulatedLeagueRankingRewardDetailComponent>;
    const route = ({ data: of({ mRegulatedLeagueRankingReward: new MRegulatedLeagueRankingReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRegulatedLeagueRankingRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MRegulatedLeagueRankingRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRegulatedLeagueRankingRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mRegulatedLeagueRankingReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
