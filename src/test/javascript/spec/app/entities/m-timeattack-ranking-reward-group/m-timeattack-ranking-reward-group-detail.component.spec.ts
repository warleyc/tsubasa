/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackRankingRewardGroupDetailComponent } from 'app/entities/m-timeattack-ranking-reward-group/m-timeattack-ranking-reward-group-detail.component';
import { MTimeattackRankingRewardGroup } from 'app/shared/model/m-timeattack-ranking-reward-group.model';

describe('Component Tests', () => {
  describe('MTimeattackRankingRewardGroup Management Detail Component', () => {
    let comp: MTimeattackRankingRewardGroupDetailComponent;
    let fixture: ComponentFixture<MTimeattackRankingRewardGroupDetailComponent>;
    const route = ({ data: of({ mTimeattackRankingRewardGroup: new MTimeattackRankingRewardGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackRankingRewardGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTimeattackRankingRewardGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackRankingRewardGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTimeattackRankingRewardGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
