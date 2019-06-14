/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageUpdateComponent } from 'app/entities/m-quest-stage/m-quest-stage-update.component';
import { MQuestStageService } from 'app/entities/m-quest-stage/m-quest-stage.service';
import { MQuestStage } from 'app/shared/model/m-quest-stage.model';

describe('Component Tests', () => {
  describe('MQuestStage Management Update Component', () => {
    let comp: MQuestStageUpdateComponent;
    let fixture: ComponentFixture<MQuestStageUpdateComponent>;
    let service: MQuestStageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestStageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestStageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestStageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestStage(123);
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
        const entity = new MQuestStage();
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
