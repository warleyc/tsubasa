/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonRankingRewardGroupDetailComponent } from 'app/entities/m-marathon-ranking-reward-group/m-marathon-ranking-reward-group-detail.component';
import { MMarathonRankingRewardGroup } from 'app/shared/model/m-marathon-ranking-reward-group.model';

describe('Component Tests', () => {
  describe('MMarathonRankingRewardGroup Management Detail Component', () => {
    let comp: MMarathonRankingRewardGroupDetailComponent;
    let fixture: ComponentFixture<MMarathonRankingRewardGroupDetailComponent>;
    const route = ({ data: of({ mMarathonRankingRewardGroup: new MMarathonRankingRewardGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonRankingRewardGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonRankingRewardGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonRankingRewardGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonRankingRewardGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
