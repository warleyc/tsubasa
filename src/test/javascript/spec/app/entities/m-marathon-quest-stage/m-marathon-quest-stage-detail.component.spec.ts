/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestStageDetailComponent } from 'app/entities/m-marathon-quest-stage/m-marathon-quest-stage-detail.component';
import { MMarathonQuestStage } from 'app/shared/model/m-marathon-quest-stage.model';

describe('Component Tests', () => {
  describe('MMarathonQuestStage Management Detail Component', () => {
    let comp: MMarathonQuestStageDetailComponent;
    let fixture: ComponentFixture<MMarathonQuestStageDetailComponent>;
    const route = ({ data: of({ mMarathonQuestStage: new MMarathonQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
