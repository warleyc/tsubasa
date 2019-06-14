/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestWorldUpdateComponent } from 'app/entities/m-marathon-quest-world/m-marathon-quest-world-update.component';
import { MMarathonQuestWorldService } from 'app/entities/m-marathon-quest-world/m-marathon-quest-world.service';
import { MMarathonQuestWorld } from 'app/shared/model/m-marathon-quest-world.model';

describe('Component Tests', () => {
  describe('MMarathonQuestWorld Management Update Component', () => {
    let comp: MMarathonQuestWorldUpdateComponent;
    let fixture: ComponentFixture<MMarathonQuestWorldUpdateComponent>;
    let service: MMarathonQuestWorldService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestWorldUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonQuestWorldUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonQuestWorldUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonQuestWorldService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonQuestWorld(123);
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
        const entity = new MMarathonQuestWorld();
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
