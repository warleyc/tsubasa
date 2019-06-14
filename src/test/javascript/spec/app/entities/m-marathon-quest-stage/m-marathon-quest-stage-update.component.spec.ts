/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestStageUpdateComponent } from 'app/entities/m-marathon-quest-stage/m-marathon-quest-stage-update.component';
import { MMarathonQuestStageService } from 'app/entities/m-marathon-quest-stage/m-marathon-quest-stage.service';
import { MMarathonQuestStage } from 'app/shared/model/m-marathon-quest-stage.model';

describe('Component Tests', () => {
  describe('MMarathonQuestStage Management Update Component', () => {
    let comp: MMarathonQuestStageUpdateComponent;
    let fixture: ComponentFixture<MMarathonQuestStageUpdateComponent>;
    let service: MMarathonQuestStageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestStageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonQuestStageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonQuestStageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonQuestStageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonQuestStage(123);
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
        const entity = new MMarathonQuestStage();
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
