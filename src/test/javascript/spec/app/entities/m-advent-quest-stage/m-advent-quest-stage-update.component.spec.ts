/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestStageUpdateComponent } from 'app/entities/m-advent-quest-stage/m-advent-quest-stage-update.component';
import { MAdventQuestStageService } from 'app/entities/m-advent-quest-stage/m-advent-quest-stage.service';
import { MAdventQuestStage } from 'app/shared/model/m-advent-quest-stage.model';

describe('Component Tests', () => {
  describe('MAdventQuestStage Management Update Component', () => {
    let comp: MAdventQuestStageUpdateComponent;
    let fixture: ComponentFixture<MAdventQuestStageUpdateComponent>;
    let service: MAdventQuestStageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestStageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MAdventQuestStageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MAdventQuestStageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAdventQuestStageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MAdventQuestStage(123);
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
        const entity = new MAdventQuestStage();
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
