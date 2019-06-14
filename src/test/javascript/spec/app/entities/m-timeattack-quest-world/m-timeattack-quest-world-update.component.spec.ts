/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackQuestWorldUpdateComponent } from 'app/entities/m-timeattack-quest-world/m-timeattack-quest-world-update.component';
import { MTimeattackQuestWorldService } from 'app/entities/m-timeattack-quest-world/m-timeattack-quest-world.service';
import { MTimeattackQuestWorld } from 'app/shared/model/m-timeattack-quest-world.model';

describe('Component Tests', () => {
  describe('MTimeattackQuestWorld Management Update Component', () => {
    let comp: MTimeattackQuestWorldUpdateComponent;
    let fixture: ComponentFixture<MTimeattackQuestWorldUpdateComponent>;
    let service: MTimeattackQuestWorldService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackQuestWorldUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTimeattackQuestWorldUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTimeattackQuestWorldUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTimeattackQuestWorldService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTimeattackQuestWorld(123);
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
        const entity = new MTimeattackQuestWorld();
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
