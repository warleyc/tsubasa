/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestWorldUpdateComponent } from 'app/entities/m-weekly-quest-world/m-weekly-quest-world-update.component';
import { MWeeklyQuestWorldService } from 'app/entities/m-weekly-quest-world/m-weekly-quest-world.service';
import { MWeeklyQuestWorld } from 'app/shared/model/m-weekly-quest-world.model';

describe('Component Tests', () => {
  describe('MWeeklyQuestWorld Management Update Component', () => {
    let comp: MWeeklyQuestWorldUpdateComponent;
    let fixture: ComponentFixture<MWeeklyQuestWorldUpdateComponent>;
    let service: MWeeklyQuestWorldService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestWorldUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MWeeklyQuestWorldUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MWeeklyQuestWorldUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MWeeklyQuestWorldService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MWeeklyQuestWorld(123);
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
        const entity = new MWeeklyQuestWorld();
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
