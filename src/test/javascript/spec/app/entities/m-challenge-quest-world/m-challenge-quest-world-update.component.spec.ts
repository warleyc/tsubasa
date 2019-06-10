/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestWorldUpdateComponent } from 'app/entities/m-challenge-quest-world/m-challenge-quest-world-update.component';
import { MChallengeQuestWorldService } from 'app/entities/m-challenge-quest-world/m-challenge-quest-world.service';
import { MChallengeQuestWorld } from 'app/shared/model/m-challenge-quest-world.model';

describe('Component Tests', () => {
  describe('MChallengeQuestWorld Management Update Component', () => {
    let comp: MChallengeQuestWorldUpdateComponent;
    let fixture: ComponentFixture<MChallengeQuestWorldUpdateComponent>;
    let service: MChallengeQuestWorldService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestWorldUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MChallengeQuestWorldUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MChallengeQuestWorldUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChallengeQuestWorldService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MChallengeQuestWorld(123);
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
        const entity = new MChallengeQuestWorld();
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
