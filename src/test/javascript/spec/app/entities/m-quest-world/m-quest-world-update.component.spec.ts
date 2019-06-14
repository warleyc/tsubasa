/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestWorldUpdateComponent } from 'app/entities/m-quest-world/m-quest-world-update.component';
import { MQuestWorldService } from 'app/entities/m-quest-world/m-quest-world.service';
import { MQuestWorld } from 'app/shared/model/m-quest-world.model';

describe('Component Tests', () => {
  describe('MQuestWorld Management Update Component', () => {
    let comp: MQuestWorldUpdateComponent;
    let fixture: ComponentFixture<MQuestWorldUpdateComponent>;
    let service: MQuestWorldService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestWorldUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestWorldUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestWorldUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestWorldService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestWorld(123);
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
        const entity = new MQuestWorld();
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
