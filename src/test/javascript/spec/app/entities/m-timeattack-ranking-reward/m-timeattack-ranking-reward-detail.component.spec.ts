/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackRankingRewardDetailComponent } from 'app/entities/m-timeattack-ranking-reward/m-timeattack-ranking-reward-detail.component';
import { MTimeattackRankingReward } from 'app/shared/model/m-timeattack-ranking-reward.model';

describe('Component Tests', () => {
  describe('MTimeattackRankingReward Management Detail Component', () => {
    let comp: MTimeattackRankingRewardDetailComponent;
    let fixture: ComponentFixture<MTimeattackRankingRewardDetailComponent>;
    const route = ({ data: of({ mTimeattackRankingReward: new MTimeattackRankingReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackRankingRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTimeattackRankingRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackRankingRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTimeattackRankingReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
