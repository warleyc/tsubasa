/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestStageDetailComponent } from 'app/entities/m-guerilla-quest-stage/m-guerilla-quest-stage-detail.component';
import { MGuerillaQuestStage } from 'app/shared/model/m-guerilla-quest-stage.model';

describe('Component Tests', () => {
  describe('MGuerillaQuestStage Management Detail Component', () => {
    let comp: MGuerillaQuestStageDetailComponent;
    let fixture: ComponentFixture<MGuerillaQuestStageDetailComponent>;
    const route = ({ data: of({ mGuerillaQuestStage: new MGuerillaQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuerillaQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuerillaQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuerillaQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
