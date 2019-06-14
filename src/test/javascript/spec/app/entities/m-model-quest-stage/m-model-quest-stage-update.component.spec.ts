/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelQuestStageUpdateComponent } from 'app/entities/m-model-quest-stage/m-model-quest-stage-update.component';
import { MModelQuestStageService } from 'app/entities/m-model-quest-stage/m-model-quest-stage.service';
import { MModelQuestStage } from 'app/shared/model/m-model-quest-stage.model';

describe('Component Tests', () => {
  describe('MModelQuestStage Management Update Component', () => {
    let comp: MModelQuestStageUpdateComponent;
    let fixture: ComponentFixture<MModelQuestStageUpdateComponent>;
    let service: MModelQuestStageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelQuestStageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MModelQuestStageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MModelQuestStageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MModelQuestStageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MModelQuestStage(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MModelQuestStage();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
