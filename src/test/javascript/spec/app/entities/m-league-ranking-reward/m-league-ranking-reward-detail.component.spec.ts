/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueRankingRewardDetailComponent } from 'app/entities/m-league-ranking-reward/m-league-ranking-reward-detail.component';
import { MLeagueRankingReward } from 'app/shared/model/m-league-ranking-reward.model';

describe('Component Tests', () => {
  describe('MLeagueRankingReward Management Detail Component', () => {
    let comp: MLeagueRankingRewardDetailComponent;
    let fixture: ComponentFixture<MLeagueRankingRewardDetailComponent>;
    const route = ({ data: of({ mLeagueRankingReward: new MLeagueRankingReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueRankingRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLeagueRankingRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLeagueRankingRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLeagueRankingReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
