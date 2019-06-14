/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestWorldDetailComponent } from 'app/entities/m-quest-world/m-quest-world-detail.component';
import { MQuestWorld } from 'app/shared/model/m-quest-world.model';

describe('Component Tests', () => {
  describe('MQuestWorld Management Detail Component', () => {
    let comp: MQuestWorldDetailComponent;
    let fixture: ComponentFixture<MQuestWorldDetailComponent>;
    const route = ({ data: of({ mQuestWorld: new MQuestWorld(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestWorldDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestWorldDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestWorldDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestWorld).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
